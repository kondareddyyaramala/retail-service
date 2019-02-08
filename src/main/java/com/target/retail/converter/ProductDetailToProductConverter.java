package com.target.retail.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.target.retail.model.Product;
import com.target.retail.service.client.model.ProductDetail;

@Component
public class ProductDetailToProductConverter implements Converter<ProductDetail, com.target.retail.model.Product> {

	@Override
	public com.target.retail.model.Product convert(ProductDetail source) {
		Product product = new Product();
		product.setId(source.getProduct().getItem().getTcin());
		product.setName(source.getProduct().getItem().getProduct_description().getTitle());
		return product;
	}

}
