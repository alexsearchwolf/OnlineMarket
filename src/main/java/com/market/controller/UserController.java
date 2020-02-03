package com.market.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.market.entities.AddressEntity;
import com.market.entities.BasketEntity;
import com.market.entities.RoleEntity;
import com.market.entities.UserEntity;
import com.market.entities.dto.LoginDto;
import com.market.entities.dto.LoginResponseDto;
import com.market.entities.dto.UserDto;
import com.market.entities.dto.UserResponseDto;
import com.market.repository.AddressRepository;
import com.market.repository.BasketRepository;
import com.market.repository.UserRepository;
import com.market.services.UserDao;
import com.market.util.Encryption;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private UserDao userDao;
	
	@CrossOrigin
    @Secured("ROLE_ADMIN")
    @RequestMapping(path="/users", method = RequestMethod.GET)
    public ResponseEntity<?> listUsersAdmin(){
		ArrayList<UserResponseDto> list = new ArrayList<>(); 
		for (int id=5;id<100;id++) {
			UserEntity user = userRepository.findOne(id);
			if (user!=null) {
				String name = user.getName();
				String lastname = user.getLastname();
				String email = user.getEmail();
				String city = user.getAddress().getCity();
				String street = user.getAddress().getStreet();
				Double suma = user.getBasket().getSuma();
				String role;
				if (user.getRole().getId()==2) {
					role = "user";
				}
				else
					role = "admin";				
				UserResponseDto respo = new UserResponseDto(id,name,lastname, email, street, city, role, suma);
			    list.add(respo);
			}
			else
				continue;
		}
	    return new ResponseEntity<ArrayList<UserResponseDto>>(list,HttpStatus.OK);
    }
	@CrossOrigin
    @Secured("ROLE_ADMIN")
	@RequestMapping (method = RequestMethod.POST, value="/signin")
	public ResponseEntity<?> signin(@RequestBody UserDto userdto){
    	UserEntity user = new UserEntity();
    	RoleEntity role= new RoleEntity();
    	BasketEntity basket = new BasketEntity();
		List<Integer> ids = userDao.findIDByEmail(userdto.getEmail());
		if (ids.isEmpty()){
			user.setEmail(userdto.getEmail());
			user.setName(userdto.getName());
			user.setLastname(userdto.getLastname());
			AddressEntity address = new AddressEntity();
			address.setCity(userdto.getCity());
			address.setStreet(userdto.getStreet());
			addressRepository.save(address);
			user.setAddress(address);
			user.setPassword(Encryption.getPassEncoded(userdto.getPass()));
			role.setId(2);
			role.setName("ROLE_USER");
			user.setRole(role);
			basket.setId(user.getId());
			user.setBasket(basket);
			basket.setSuma(0.00);
			basketRepository.save(basket);
			userRepository.save(user);			
			return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
			}		
		else
			return new ResponseEntity<String>("Access Denied - user is added in db by id:"+ ids,HttpStatus.OK);		
		}

    @CrossOrigin
    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping (method = RequestMethod.POST, value="/login")
	public ResponseEntity<?> login(@RequestBody LoginDto logdto){   	
		List<String> passes = userDao.findPassByEmail(logdto.getEmail());
		if ((passes).isEmpty()) {
			//return new ResponseEntity<RestError>(new RestError("Invalid email and password"),HttpStatus.INTERNAL_SERVER_ERROR);		
			return new ResponseEntity<String>("Invalid email and password",HttpStatus.INTERNAL_SERVER_ERROR);					
		}
		String enpass = passes.get(0);
		if ((Encryption.getPassMatch(logdto.getPass(),enpass)) && !(passes).isEmpty() ){
			Integer id= userDao.findIDByEmail(logdto.getEmail()).get(0);
			LoginResponseDto respo = new LoginResponseDto(id, logdto.getEmail(), logdto.getPass());
			return new ResponseEntity<LoginResponseDto>(respo,HttpStatus.OK) ;}
		else
			//return new ResponseEntity<RestError>(new RestError("Invalid email and password"),HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<String>("Invalid email and password",HttpStatus.INTERNAL_SERVER_ERROR);
    
    } 
    
    @CrossOrigin
    @Secured({"ROLE_ADMIN"})
	@RequestMapping (method = RequestMethod.DELETE, value="/userDelete/{id}")
	public ResponseEntity<String> userDelete(@PathVariable Integer id){  
    	userRepository.delete(id);   	
    	return new ResponseEntity<String>("Deleted user with id:"+ id,HttpStatus.OK);	
    }  
    
    
    @CrossOrigin
    @Secured({"ROLE_ADMIN"})
	@RequestMapping (method = RequestMethod.PUT, value="/userUpdate/{id}")
	public ResponseEntity<String> userUpdate(@PathVariable Integer id, @RequestBody UserEntity user){  
    	userDao.update(id, user);
    	return new ResponseEntity<String>("Updated user with id:"+ id,HttpStatus.OK);	
    } 
}

    	
