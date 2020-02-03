package com.market.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.market.entities.BasketEntity;
import com.market.entities.ProductEntity;
import com.market.entities.UserEntity;
import com.market.entities.dto.UserDto;
import com.market.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findIDByEmail(String email) {

		String sql = "select id from UserEntity where email=:email";
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		List<Integer> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findUserByID(Integer id) {

		String sql = "select u from UserEntity u where u.id=:id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		List<UserEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findPassByEmail(String email) {

		String sql = "select password from UserEntity where email=:email";
		Query query = em.createQuery(sql);
		query.setParameter("email", email);

		List<String> result = new ArrayList<>();
		result = query.getResultList();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Iterable<ProductEntity>> listProductsInBasket(Integer basket) {

		String sql = "SELECT basket_products from BasketEntity b where b.id=:basket";
		Query query = em.createQuery(sql);
		query.setParameter("basket", basket);
		List<Iterable<ProductEntity>> result = new ArrayList<Iterable<ProductEntity>>();
		result = query.getResultList();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getAllPricesFromBasket(Integer basket) {

		Query q = em.createNativeQuery("SELECT suma from basketsum b where b.basket=:basket");
		q.setParameter("basket", basket);
		List<Double> sum = q.getResultList();
		return sum;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BasketEntity> getBasketByID(Integer id) {

		String sql = "select u from BasketEntity u where u.id=:id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		List<BasketEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> getProductByID(Integer id) {

		String sql = "select u from ProductEntity u where u.id=:id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		List<ProductEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;	
	}

	  @Transactional
	  @Override 
	  public void deleteProductFromBasket (Integer product_id, Integer basket_id) {
	  
	    Query q = em.createNativeQuery("DELETE from product_basket where product_id=:product_id and basket_id=:basket_id"); 
	    q.setParameter("product_id", product_id); 
	    q.setParameter("basket_id", basket_id); 
	    q.executeUpdate();
	  }
	  
	  @Transactional
	  @Override 
	  public void deleteAllFromBasket (Integer basket_id) {
		 Query q = em.createNativeQuery("DELETE from product_basket where basket_id=:basket_id"); 
		 q.setParameter("basket_id", basket_id); 
		 q.executeUpdate();
	  }	  
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductByCategories(Integer category1, Integer category2, Integer category3,
			Integer category4, Double category5) {

		List<Integer> products1 = new ArrayList<Integer>();
		List<Integer> products2 = new ArrayList<Integer>();
		List<Integer> products3 = new ArrayList<Integer>();
		List<Integer> products4 = new ArrayList<Integer>();
		List<Integer> products5 = new ArrayList<Integer>();

		if (category1 != 0) {
			Query q12 = em.createNativeQuery("select product_id from product where color=:category1");
			q12.setParameter("category1", category1);
			products1 = q12.getResultList();
		} else {
			Query q = em.createNativeQuery("select product_id from product");
			products1 = q.getResultList();
		}
		if (category2 != 0) {
			Query q22 = em.createNativeQuery("select product_id from product where item=:category2");
			q22.setParameter("category2", category2);
			products2 = q22.getResultList();
		} else {
			Query q = em.createNativeQuery("select product_id from product");
			products2 = q.getResultList();
		}
		if (category3 != 0) {
			Query q32 = em.createNativeQuery("select product_id from product where size=:category3");
			q32.setParameter("category3", category3);
			products3 = q32.getResultList();
		} else {
			Query q = em.createNativeQuery("select product_id from product");
			products3 = q.getResultList();
		}
		if (category4 != 0) {
			Query q42 = em.createNativeQuery("select product_id from product where sale=:category4");
			q42.setParameter("category4", category4);
			products4 = q42.getResultList();
		} else {
			Query q = em.createNativeQuery("select product_id from product");
			products4 = q.getResultList();
		}
		if (category5 != 100000) {
			Query q52 = em.createNativeQuery("select product_id from product where product_price<:category5");
			q52.setParameter("category5", category5);
			products5 = q52.getResultList();
		} else {
			Query q = em.createNativeQuery("select product_id from product");
			products5 = q.getResultList();
		}

		Query q = em.createNativeQuery("select product_id from product");
		List<Integer> prod = q.getResultList();

		List<Integer> prodCat = new ArrayList<Integer>();

		for (Integer k : prod) {
			if (products1.contains(k) && products2.contains(k) && products3.contains(k) && products4.contains(k)
					&& products5.contains(k)) {
				prodCat.add(k);
			}
		}

		return prodCat;


	}
	
	public UserEntity update(Integer id, UserEntity userNew) {
		UserEntity user = userRepository.findOne(id);
		user.setName(userNew.getName());
		user.setLastname(userNew.getLastname());
		user.setEmail(userNew.getEmail());
		user.setPassword(userNew.getPassword());
		return user;
	}
}
