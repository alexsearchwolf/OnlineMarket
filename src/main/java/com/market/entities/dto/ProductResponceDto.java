package com.market.entities.dto;

public class ProductResponceDto {
	

	public Integer id;
	public String name;
	public Double price;
	public Integer stock;
	public String size_name;
	public String onSale ;
	public String color_name;
	public String item_name;
	public ProductResponceDto() {
		super();
	}
	public ProductResponceDto(Integer id, String name, Double price, Integer stock, String size_name, String onSale,
			String color_name, String item_name) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.size_name = size_name;
		this.onSale = onSale;
		this.color_name = color_name;
		this.item_name = item_name;
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
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}
	public String getOnSale() {
		return onSale;
	}
	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	
	
}
	