package com.example.retail.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.retail.model.Product;
import com.example.retail.service.client.model.ProductDetail;

@Component
public class ProductDetailToProductConverter implements Converter<ProductDetail, com.example.retail.model.Product> {

	@Override
	public com.example.retail.model.Product convert(ProductDetail source) {
		Product product = new Product();
		product.setId(source.getProduct().getItem().getTcin());
		product.setName(source.getProduct().getItem().getProduct_description().getTitle());
		return product;
	}

}
