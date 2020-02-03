package com.market.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {

	@JsonProperty
	private String name;
	@JsonProperty
	private Double price;
	@JsonProperty
	private Integer size_id;
	@JsonProperty
	private Integer sale_id;
	@JsonProperty
	private Integer item_id;
	@JsonProperty
	private Integer color_id;
	@JsonProperty
	private Integer stock;
	public ProductDto() {
		super();
	}
	public ProductDto(String name, Double price, Integer size_id, Integer sale_id, Integer item_id,
			Integer color_id, Integer stock) {
		super();
		this.name = name;
		this.price = price;
		this.size_id = size_id;
		this.sale_id = sale_id;
		this.item_id = item_id;
		this.color_id = color_id;
		this.stock = stock;
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
	public Integer getSize_id() {
		return size_id;
	}
	public void setSize_id(Integer size_id) {
		this.size_id = size_id;
	}
	public Integer getSale_id() {
		return sale_id;
	}
	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public Integer getColor_id() {
		return color_id;
	}
	public void setColor_id(Integer color_id) {
		this.color_id = color_id;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	

}
