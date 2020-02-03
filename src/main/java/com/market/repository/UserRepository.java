package com.market.repository;

import org.springframework.data.repository.CrudRepository;

import com.market.entities.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Integer>{
}
