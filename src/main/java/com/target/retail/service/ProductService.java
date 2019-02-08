package com.target.retail.service;

import com.target.retail.model.Product;

public interface ProductService {

	public Product getProduct(final Integer id);

	public Product updateProduct(final Product product);

}
