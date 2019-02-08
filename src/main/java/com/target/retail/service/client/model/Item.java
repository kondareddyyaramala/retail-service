package com.target.retail.service.client.model;

public class Item {
	public ProductDescription product_description;
	public Integer tcin;

	public ProductDescription getProduct_description() {
		return product_description;
	}

	public void setProduct_description(ProductDescription product_description) {
		this.product_description = product_description;
	}

	public Integer getTcin() {
		return tcin;
	}

	public void setTcin(Integer tcin) {
		this.tcin = tcin;
	}

}
