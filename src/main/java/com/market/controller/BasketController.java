package com.market.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.market.controller.util.RestError;
import com.market.entities.AddressEntity;
import com.market.entities.BasketEntity;
import com.market.entities.ProductEntity;
import com.market.entities.UserEntity;
import com.market.repository.BasketRepository;
import com.market.repository.ProductRepository;
import com.market.services.EmailService;
import com.market.services.UserDao;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class BasketController {

	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailService emailService;

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getAllBasket(@PathVariable("id") Integer id) {
		return new ResponseEntity<BasketEntity>(basketRepository.findOne(id), HttpStatus.OK);
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/products")
	public ResponseEntity<?> getAllProductsFromBasket(@PathVariable("id") Integer id) {
		List<Iterable<ProductEntity>> productList = userDao.listProductsInBasket(id);
		return new ResponseEntity<List<Iterable<ProductEntity>>>(productList, HttpStatus.OK);
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/sum")
	public ResponseEntity<?> getBasketSum(@PathVariable("id") Integer id) {
		List<Double> pricesList = userDao.getAllPricesFromBasket(id);
		if (pricesList.isEmpty()) {
			return new ResponseEntity<Double>(0.00, HttpStatus.OK);
		}
		else {
			Double price = pricesList.get(0);
			return new ResponseEntity<Double>(price, HttpStatus.OK) ;
		}
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/mail")
	public ResponseEntity<?> sendMail(@PathVariable("id") Integer id) {
		try {
			List<UserEntity> pricesList = userDao.findUserByID(id);
		    UserEntity user = pricesList.get(0);
		    BasketEntity basket = user.getBasket();
		    AddressEntity address = user.getAddress();
		    List<Double> prSum = userDao.getAllPricesFromBasket(id);   
		    Double suma = prSum.get(0);
		    basket.setSuma(suma);
		    List<ProductEntity> products = basket.getBasket_products();
		    for (ProductEntity i : products) {
		    	Integer oldStock = i.getStock();
		    	Integer newStock = oldStock - 1;
		    	i.setStock(newStock);
		    }
		    emailService.sendTemplateMessage(user,basket,address, "Mail with products from basket no."+basket.getId());
		    userDao.deleteAllFromBasket(id);
			basket.setSuma(0.0);
		    basketRepository.save(basket);
		    return new ResponseEntity<BasketEntity>(basket, HttpStatus.OK);
	}
	catch(Exception e){
		return new ResponseEntity<RestError>(new RestError("Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}	
	@CrossOrigin
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping (method = RequestMethod.POST, value="/{idd}/addProductBasket")
	public ResponseEntity<?> addProductBasket(@PathVariable("idd") Integer id, @RequestParam("product") Integer product_id){
		
		List<BasketEntity> list = userDao.getBasketByID(id);
		List<ProductEntity> products = userDao.getProductByID(product_id);
		ProductEntity prod = products.get(0);
		prod.setProduct_baskets(list);
		Double cena = prod.getPrice();
		Double suma = list.get(0).getSuma();
	    list.get(0).setSuma(cena+suma);
		productRepository.save(prod);
		basketRepository.save(list);
		List<Iterable<ProductEntity>> productList = userDao.listProductsInBasket(id);
		
		return new ResponseEntity<List<Iterable<ProductEntity>>>(productList, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping (method = RequestMethod.POST, value="/{id}/deleteAllProductsBasket")
	public ResponseEntity<?> deleteProductsFromBasket(@PathVariable("id") Integer id){
		
		userDao.deleteAllFromBasket(id);
		List<BasketEntity> l = userDao.getBasketByID(id);
		l.get(0).setSuma(0.0);
		basketRepository.save(l);
		return new ResponseEntity<List<BasketEntity>>(l, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping (method = RequestMethod.POST, value="/{id}/deleteProductBasket")
	public ResponseEntity<?> deleteProductBasket(@PathVariable("id") Integer idb, @RequestParam("product") Integer idp){
		
		userDao.deleteProductFromBasket(idp, idb);
		List<BasketEntity> l = userDao.getBasketByID(idb);
		List<ProductEntity> products = userDao.getProductByID(idp);
		Double cena = products.get(0).getPrice();
		Double suma = l.get(0).getSuma();
		l.get(0).setSuma(suma-cena);
		basketRepository.save(l);
		
		return new ResponseEntity<List<BasketEntity>>(l, HttpStatus.OK);
	}
}
