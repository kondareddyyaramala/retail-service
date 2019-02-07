package com.example.retail.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.retail.service.client.ProductDetailServiceClient;
import com.example.retail.service.client.model.ProductDetail;

@Service
public class ProductDetailServiceClientImpl implements ProductDetailServiceClient {

	private RestTemplate restTemplate;

	@Value("${product-service.base-url}")
	private String productServiceBaseUrl;

	private String requestParams = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

	@Autowired
	public ProductDetailServiceClientImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public ProductDetail getProduct(Integer productId) {
		String url = String.format(productServiceBaseUrl + "%s" + requestParams, productId);
		ProductDetail productDetail = restTemplate.getForEntity(url, ProductDetail.class).getBody();
		return productDetail;
	}

}
