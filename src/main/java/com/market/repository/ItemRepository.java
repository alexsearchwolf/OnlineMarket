package com.market.repository;

import org.springframework.data.repository.CrudRepository;

import com.market.entities.ItemEntity;

public interface ItemRepository extends CrudRepository<ItemEntity, Integer>{

}
