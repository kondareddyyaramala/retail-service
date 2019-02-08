package com.target.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.retail.model.Product;
import com.target.retail.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable final Integer id) {
		return productService.getProduct(id);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody final Product product) {
		return productService.updateProduct(product);
	}

}
