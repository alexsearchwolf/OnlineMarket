package com.market.services;

import java.util.List;

import com.market.entities.BasketEntity;
import com.market.entities.ProductEntity;
import com.market.entities.UserEntity;

public interface UserDao {

	public List<Integer> findIDByEmail(String email);
	
	public List<UserEntity> findUserByID(Integer id);
	
	public List<String> findPassByEmail(String email);
	
	public List<Iterable<ProductEntity>> listProductsInBasket(Integer basket);
	
	public List<Double> getAllPricesFromBasket(Integer basket);
	
	public List<Integer> getProductByCategories(Integer category1, Integer category2, Integer category3, Integer category4, Double category5);

	public List<BasketEntity> getBasketByID(Integer id);
	
	public List<ProductEntity> getProductByID(Integer id);
	
	public void deleteProductFromBasket (Integer product_id, Integer basket_id);
	
	public void deleteAllFromBasket (Integer basket_id);
	
	public UserEntity update(Integer id, UserEntity user);
	
}
