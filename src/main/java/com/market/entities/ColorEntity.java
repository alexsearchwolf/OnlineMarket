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
@Table(name = "color")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ColorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "color_id")
	@JsonView(Views.Admin.class)
	private Integer color_id;	
	@JsonView(Views.User.class)
	@Column(name = "color_name")
	private String color_name;
	
	public Integer getColor_id() {
		return color_id;
	}
	public void setColor_id(Integer color_id) {
		this.color_id = color_id;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public ColorEntity(Integer color_id, String color_name) {
		super();
		this.color_id = color_id;
		this.color_name = color_name;
	}
	public ColorEntity() {
		super();
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "color", fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH })
	private List<ProductEntity> products = new ArrayList<>();

}
