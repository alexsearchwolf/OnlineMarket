package com.market.repository;

import org.springframework.data.repository.CrudRepository;

import com.market.entities.BasketEntity;

public interface ProductBasketRepository extends CrudRepository<BasketEntity,Integer>{

}
