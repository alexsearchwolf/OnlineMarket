package com.market.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.market.security.Views;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Admin.class)
	@Column(name = "user_id")
	private Integer id;
	@JsonView(Views.Admin.class)
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	@JsonIgnore
	private String password;
	@JsonView(Views.User.class)
	@Column(name = "name")
	private String name;
	@JsonView(Views.User.class)
	@Column(name = "last_name")
	private String lastname;
	
	
	@JsonView(Views.Admin.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;
	
	@JsonManagedReference
	@JsonView(Views.Admin.class)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "basket")
	protected BasketEntity basket;
	
	@JsonManagedReference
	@JsonView(Views.Admin.class)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	protected AddressEntity address;
	

	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public BasketEntity getBasket() {
		return basket;
	}
	public void setBasket(BasketEntity basket) {
		this.basket = basket;
	}
	public Integer getId() {
		return id;
	}
	public UserEntity() {
		super();
	}
	public AddressEntity getAddress() {
		return address;
	}
	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	public UserEntity(Integer id, String email, String password, String name, String lastname, RoleEntity role,
			BasketEntity basket, AddressEntity address) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
		this.basket = basket;
		this.address = address;
	}



}
