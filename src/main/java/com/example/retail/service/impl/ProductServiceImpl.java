package com.example.retail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retail.converter.PriceEntityToPrice;
import com.example.retail.converter.ProductDetailToProductConverter;
import com.example.retail.entity.Price;
import com.example.retail.model.Product;
import com.example.retail.repository.PriceRepository;
import com.example.retail.service.ProductService;
import com.example.retail.service.client.ProductDetailServiceClient;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDetailServiceClient productDetailServiceClient;
	private final ProductDetailToProductConverter productDetailToProductConverter;
	private final PriceEntityToPrice priceEntityToPrice;
	private final PriceRepository priceRepository;

	@Autowired
	public ProductServiceImpl(final ProductDetailServiceClient productDetailServiceClient,
			final ProductDetailToProductConverter productDetailToProductConverter,
			final PriceRepository priceRepository, final PriceEntityToPrice priceEntityToPrice) {
		this.priceRepository = priceRepository;
		this.priceEntityToPrice = priceEntityToPrice;
		this.productDetailServiceClient = productDetailServiceClient;
		this.productDetailToProductConverter = productDetailToProductConverter;
	}

	@Override
	public Product getProduct(final Integer id) {
		// make service call to get the product information
		Product product = productDetailToProductConverter.convert(productDetailServiceClient.getProduct(id));

		// make a data base call to get the price associated with this item
		Price price = priceRepository.findByProductId(id);

		// convert the price and set it on the product
		product.setCurrent_price(priceEntityToPrice.convert(price));

		return product;
	}

	@Override
	public Product updateProduct(final Product product) {
		// make service call to get the product information

		// get the price entity
		Price price = priceRepository.findByProductId(product.getId());
		
		// if there is no price present for this, then create a new one
		if(price == null) {
			price = new Price();
			price.setId(1);
			price.setProductId(product.getId());
			price.setCurrencyCode("US");
		}

		// set the new price
		price.setValue(product.getCurrent_price().getValue());

		// now persist the entity
		priceRepository.save(price);

		// make a data base call to get the price associated with this item
		return product;
	}

}
