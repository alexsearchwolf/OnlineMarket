package com.market.repository;

import org.springframework.data.repository.CrudRepository;
import com.market.entities.ProductEntity;


public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{

}
