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
@Table(name = "size")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SizeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "size_id")
	@JsonView(Views.Admin.class)
	private Integer size_id;	
	@JsonView(Views.User.class)
	@Column(name = "size_name")
	private String size_name;
	
	public Integer getSize_id() {
		return size_id;
	}
	public void setSize_id(Integer size_id) {
		this.size_id = size_id;
	}
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}
	public SizeEntity(Integer size_id, String size_name) {
		super();
		this.size_id = size_id;
		this.size_name = size_name;
	}
	public SizeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "size", fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH })
	private List<ProductEntity> products = new ArrayList<>();
	

}
