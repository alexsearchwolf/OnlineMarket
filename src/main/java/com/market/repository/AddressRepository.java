package com.market.repository;

import org.springframework.data.repository.CrudRepository;
import com.market.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer>{

}
