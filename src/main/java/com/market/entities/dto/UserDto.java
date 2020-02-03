package com.market.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

	@NotNull(message="Email must be provided.")
	@Size(min=2, max=15, message="Email must be between {min} and {max} characters long.")
	private String email;
	@NotNull(message="Password must be provided.")
	@Size(min=2, max=15, message="Password must be between {min} and {max} characters long.")
	private String pass;
	@NotNull(message="Name must be provided.")
	@Size(min=2, max=15, message="Name must be between {min} and {max} characters long.")
	private String name;
	@NotNull(message="Lastname must be provided.")
	@Size(min=2, max=15, message="Lastname must be between {min} and {max} characters long.")
	private String lastname;
	@NotNull(message="City must be provided.")
	@Size(min=2, max=15, message="City must be between {min} and {max} characters long.")
	private String city;
	@NotNull(message="Street must be provided.")
	@Size(min=2, max=35, message="Street must be between {min} and {max} characters long.")
	private String street;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public UserDto(String email, String pass, String name, String lastname, String city, String street) {
		super();
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.lastname = lastname;
		this.city = city;
		this.street = street;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
