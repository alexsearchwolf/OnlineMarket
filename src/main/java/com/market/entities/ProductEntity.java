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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.market.security.Views;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Admin.class)
	@Column(name = "product_id")
	private Integer id;	
	@JsonView(Views.User.class)
	@Column(name = "product_name")
	private String name;
	@JsonView(Views.User.class)
	@Column(name = "product_price")
	private Double price;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "product_basket", joinColumns ={@JoinColumn(name = "product_id", nullable = false, updatable = false) },inverseJoinColumns = { @JoinColumn(name = "basket_id",nullable = false, updatable = false) })
	protected List<BasketEntity> product_baskets = new ArrayList<BasketEntity>();
	
	@JsonView(Views.User.class)
	@Column(name = "stock")
	private Integer stock;
	
	public List<BasketEntity> getProduct_baskets() {
		return product_baskets;
	}

	public void setProduct_baskets(List<BasketEntity> product_baskets) {
		this.product_baskets = product_baskets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public ProductEntity() {
		super();
	}


	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "size")
	protected SizeEntity size;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "sale")
	protected SaleEntity sale;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "item")
	protected ItemEntity item;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "color")
	protected ColorEntity color;

	public SizeEntity getSize() {
		return size;
	}

	public void setSize(SizeEntity size) {
		this.size = size;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public ColorEntity getColor() {
		return color;
	}

	public void setColor(ColorEntity color) {
		this.color = color;
	}

	public ProductEntity(Integer id, String name, Double price, List<BasketEntity> product_baskets,
			Integer stock, SizeEntity size, SaleEntity sale, ItemEntity item, ColorEntity color) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.product_baskets = product_baskets;
		this.stock = stock;
		this.size = size;
		this.sale = sale;
		this.item = item;
		this.color = color;
	}
	
	
}
