package com.market.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserResponseDto {
	
	public Integer id;
	@NotNull(message="Email must be provided.")
	private String email;
	@NotNull(message="Name must be provided.")
	@Size(min=2, max=15, message="Name must be between {min} and {max} characters long.")
	private String name;
	@NotNull(message="Lastname must be provided.")
	@Size(min=2, max=15, message="Lastname must be between {min} and {max} characters long.")
	private String lastname;
	private String role;
	private String city;	
	private String street;
	private Double suma;
	
	public UserResponseDto() {
		super();
	}
	public UserResponseDto(Integer id, String email, String name, String lastname, String role, String city,
			String street, Double suma) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
		this.city = city;
		this.street = street;
		this.suma = suma;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public Double getSuma() {
		return suma;
	}
	public void setSuma(Double suma) {
		this.suma = suma;
	}
	
	
	
	
	

}
