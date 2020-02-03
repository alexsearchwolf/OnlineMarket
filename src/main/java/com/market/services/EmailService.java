package com.market.services;

import com.market.entities.AddressEntity;
import com.market.entities.BasketEntity;
import com.market.entities.UserEntity;

public interface EmailService {

	void sendTemplateMessage(UserEntity user,BasketEntity basket,AddressEntity address, String subject) throws Exception;

}
