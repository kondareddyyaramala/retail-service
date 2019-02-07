package com.example.retail.service;

import com.example.retail.model.Product;

public interface ProductService {

	public Product getProduct(final Integer id);

	public Product updateProduct(final Product product);

}
