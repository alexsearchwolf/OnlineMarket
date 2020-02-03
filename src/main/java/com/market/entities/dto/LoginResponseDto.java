package com.market.entities.dto;

public class LoginResponseDto {

	public Integer id;
	public String email;
	public String pass;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LoginResponseDto(Integer id, String email, String pass) {
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
	}
	public LoginResponseDto() {
		super();
	}
	
	
}
