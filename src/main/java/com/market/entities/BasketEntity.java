package com.market.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.market.security.Views;

@Entity
@Table(name = "basket")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BasketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "basket_id")
	@JsonView(Views.Admin.class)
	private Integer id;	
	@JsonView(Views.User.class)
	@Column(name = "suma",nullable = true)
	private Double suma;	
	
	@JsonBackReference
	@JsonView(Views.User.class)
	@OneToOne(mappedBy = "basket", cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    protected UserEntity user;
	
	@JsonView(Views.User.class)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "product_basket", joinColumns ={@JoinColumn(name = "basket_id", nullable = false, updatable = false) },inverseJoinColumns = { @JoinColumn(name = "product_id",nullable = false, updatable = false) })
	protected List<ProductEntity> basket_products = new ArrayList<ProductEntity>();

	
	public BasketEntity(Integer id, UserEntity user, List<ProductEntity> basket_products, Double suma) {
		super();
		this.id = id;
		this.user = user;
		this.basket_products = basket_products;
		this.suma=suma;
	}

	public Double getSuma() {
		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<ProductEntity> getBasket_products() {
		return basket_products;
	}

	public void setBasket_products(List<ProductEntity> basket_products) {
		this.basket_products = basket_products;
	}

	public BasketEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	
			

}
