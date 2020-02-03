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
@Table(name = "item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	@JsonView(Views.Admin.class)
	private Integer item_id;	
	@JsonView(Views.User.class)
	@Column(name = "item_name")
	private String item_name;
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public ItemEntity(Integer item_id, String item_name) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
	}
	public ItemEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonIgnore
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH })
	private List<ProductEntity> products = new ArrayList<>();
	
}
