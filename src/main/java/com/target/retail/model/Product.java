package com.target.retail.model;

public class Product {

	private Integer id;
	private String name;
	private Price current_price;

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

	public Price getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(Price current_price) {
		this.current_price = current_price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", current_price=" + current_price + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getCurrent_price()=" + getCurrent_price() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
