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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.market.security.Views;

@Entity
@Table(name = "sale")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SaleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sale_id")
	@JsonView(Views.Admin.class)
	private Integer sale_id;	
	@JsonView(Views.User.class)
	@Column(name = "onSale")
	private Boolean onSale;
	
	public Integer getSale_id() {
		return sale_id;
	}
	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
	public Boolean getOnSale() {
		return onSale;
	}
	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}
	public SaleEntity(Integer sale_id, Boolean onSale) {
		super();
		this.sale_id = sale_id;
		this.onSale = onSale;
	}
	public SaleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	@JsonIgnore
	@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH })
	private List<ProductEntity> products = new ArrayList<>();

}
