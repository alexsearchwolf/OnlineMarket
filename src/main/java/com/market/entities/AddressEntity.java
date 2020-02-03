package com.market.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.market.security.Views;

@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	@JsonView(Views.Admin.class)
	private Integer id;	
	@JsonView(Views.User.class)
	@Column(name = "street")
	private String street;
	@JsonView(Views.User.class)
	@Column(name = "city")
	private String city;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AddressEntity() {
		super();
	}
	public AddressEntity(Integer id, String street, String city) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@JsonBackReference
	@JsonView(Views.User.class)
	@OneToOne(mappedBy = "address", cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    protected UserEntity user;
}
