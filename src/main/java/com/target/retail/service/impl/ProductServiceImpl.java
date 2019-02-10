package com.target.retail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.retail.converter.PriceEntityToPriceConverter;
import com.target.retail.converter.ProductDetailToProductConverter;
import com.target.retail.entity.Price;
import com.target.retail.model.Product;
import com.target.retail.repository.PriceRepository;
import com.target.retail.service.ProductService;
import com.target.retail.service.client.ProductDetailServiceClient;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDetailServiceClient productDetailServiceClient;
	private final ProductDetailToProductConverter productDetailToProductConverter;
	private final PriceEntityToPriceConverter priceEntityToPriceConverter;
	private final PriceRepository priceRepository;

	@Autowired
	public ProductServiceImpl(final ProductDetailServiceClient productDetailServiceClient,
			final ProductDetailToProductConverter productDetailToProductConverter,
			final PriceRepository priceRepository, final PriceEntityToPriceConverter priceEntityToPriceConverter) {
		this.priceRepository = priceRepository;
		this.priceEntityToPriceConverter = priceEntityToPriceConverter;
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
		product.setCurrent_price(priceEntityToPriceConverter.convert(price));

		return product;
	}

	@Override
	public Product updateProduct(final Product product) {
		// make service call to get the product information

		// get the price entity
		Price price = priceRepository.findByProductId(product.getId());

		// set the new price
		price.setValue(product.getCurrent_price().getValue());

		// now persist the entity
		priceRepository.save(price);

		// make a data base call to get the price associated with this item
		return product;
	}

}
