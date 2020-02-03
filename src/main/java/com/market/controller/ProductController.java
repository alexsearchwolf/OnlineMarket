package com.market.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.market.entities.ColorEntity;
import com.market.entities.ItemEntity;
import com.market.entities.ProductEntity;
import com.market.entities.SaleEntity;
import com.market.entities.SizeEntity;
import com.market.entities.dto.ProductDto;
import com.market.entities.dto.ProductResponceDto;
import com.market.repository.ColorRepository;
import com.market.repository.ItemRepository;
import com.market.repository.ProductRepository;
import com.market.repository.SaleRepository;
import com.market.repository.SizeRepository;
import com.market.services.UserDao;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class ProductController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductRepository productRepository;	
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private SaleRepository saleRepository;
	
	@CrossOrigin
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(path="/products", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<ProductResponceDto>> listUsersAdmin(){
    	ArrayList<ProductResponceDto> list = new ArrayList<>(); 
    	for (int id = 1; id < 100; id++) {
			ProductEntity product = productRepository.findOne(id);
			if (product!=null) {
				String name = product.getName();
			    Double price = product.getPrice();			    
			    Integer stock = product.getStock();	
			    SizeEntity size = product.getSize();
			    String a,b,c,d;
			    
			    if (size==null) {
			    	a ="-";
			    }
			    else
			    	a = product.getSize().getSize_name();
			    ColorEntity color = product.getColor();
			    if (color==null) {
			    	c ="-";
			    }
			    else
			    	c = product.getColor().getColor_name();
			    SaleEntity sale = product.getSale();
			    if (sale==null) {
			    	b = "-";
			    }
			    else{ 
			    	if (product.getSale().getOnSale()==false)
			    		b="no";
			    	else
			    		b="yes";
			    }
			    ItemEntity item = product.getItem();
			    if (item==null) {
			    	 d= "-";
			    }
			    else
			    	d = product.getItem().getItem_name();
			    ProductResponceDto respo = new ProductResponceDto(id,name,price,stock,a,b,c,d);
			    list.add(respo);
			}
			else
				continue;
			}
	    return new ResponseEntity<ArrayList<ProductResponceDto>>(list,HttpStatus.OK);
    }
    @CrossOrigin
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET, value = "/categories/{category1}&{category2}&{category3}&{category4}&{category5}")
	public ResponseEntity<?> getByCategories(@PathVariable Integer category1,@PathVariable Integer category2,@PathVariable Integer category3,@PathVariable Integer category4,@PathVariable Double category5) {
		
		List<Integer> ids = userDao.getProductByCategories(category1,category2,category3,category4,category5);
		
		Iterable<ProductEntity> list = productRepository.findAll(ids);
		
		return new ResponseEntity<Iterable<ProductEntity>>(list , HttpStatus.OK);
	
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping (method = RequestMethod.POST, value="/product")
	public ResponseEntity<?> postProduct(@RequestBody ProductDto product, BindingResult result){
    
		ProductEntity pr = new ProductEntity(); 
		ColorEntity color = new ColorEntity();
		ItemEntity item = new ItemEntity();
		SaleEntity sale = new SaleEntity();
		SizeEntity size = new SizeEntity();			
		pr.setName(product.getName()); 
		pr.setPrice(product.getPrice()); 
		pr.setStock(product.getStock());		
		color = colorRepository.findOne(product.getColor_id());
		item = itemRepository.findOne(product.getItem_id());
		sale = saleRepository.findOne(product.getSale_id());
		size = sizeRepository.findOne(product.getSize_id());	
		pr.setColor(color);
		pr.setItem(item);
		pr.setSale(sale);
		pr.setSize(size);
		productRepository.save(pr);
	
		return new ResponseEntity<ProductEntity>(pr, HttpStatus.OK);
	}
	
	@CrossOrigin
    @Secured({"ROLE_ADMIN"})
	@RequestMapping (method = RequestMethod.DELETE, value="/productDelete/{id}")
	public ResponseEntity<String> userDelete(@PathVariable Integer id){  
        productRepository.delete(id);   	
    	return new ResponseEntity<String>("Deleted product with id:"+ id,HttpStatus.OK);	
    } 
}
