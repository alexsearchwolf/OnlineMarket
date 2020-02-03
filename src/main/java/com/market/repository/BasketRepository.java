package com.market.repository;

import org.springframework.data.repository.CrudRepository;

import com.market.entities.BasketEntity;

public interface BasketRepository extends CrudRepository<BasketEntity, Integer>{
	
}
