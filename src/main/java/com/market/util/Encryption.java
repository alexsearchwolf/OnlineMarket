package com.market.util;

import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.market.OnlineMarketApplication;


public class Encryption {
	
	public static void main(String[] args) {
		System.out.println(getPassEncoded("pass"));
		}

	public static String getPassEncoded(String password) {
		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		 return bCryptPasswordEncoder.encode(password); 
		 } 
	
	
	public static Boolean getPassMatch(String password, String enpass) { 
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		 if (bCryptPasswordEncoder.matches(password, enpass)) {
			 return true;
		 }
		 else
			 return false;
	}
	
	

				 
	  

}
