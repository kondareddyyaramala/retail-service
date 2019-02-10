package com.target.retail.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.target.retail.converter.PriceEntityToPriceConverter;
import com.target.retail.converter.ProductDetailToProductConverter;
import com.target.retail.entity.Price;
import com.target.retail.repository.PriceRepository;
import com.target.retail.service.client.ProductDetailServiceClient;
import com.target.retail.service.client.model.Item;
import com.target.retail.service.client.model.Product;
import com.target.retail.service.client.model.ProductDescription;
import com.target.retail.service.client.model.ProductDetail;
import com.target.retail.service.impl.ProductServiceImpl;

public class ProductServiceTests {

	@Mock
	private ProductDetailServiceClient productDetailServiceClient;

	@Mock
	private PriceRepository priceRepository;

	private ProductServiceImpl productServiceImpl;

	private PriceEntityToPriceConverter priceEntityToPriceConverter;

	private ProductDetailToProductConverter productDetailToProductConverter;

	@Before
	public void beforeTestRun() {
		MockitoAnnotations.initMocks(this);
		this.productDetailToProductConverter = new ProductDetailToProductConverter();
		this.priceEntityToPriceConverter = new PriceEntityToPriceConverter();
		this.productServiceImpl = new ProductServiceImpl(productDetailServiceClient, productDetailToProductConverter,
				priceRepository, priceEntityToPriceConverter);
	}

	@Test
	public void test_getProduct() {
		ProductDetail mockProductDetail = getMockProductResponse(123);
		Price mockPrice = getMockPrice(123);

		when(productDetailServiceClient.getProduct(anyInt())).thenReturn(mockProductDetail);
		when(priceRepository.findByProductId(anyInt())).thenReturn(mockPrice);

		com.target.retail.model.Product product = productServiceImpl.getProduct(123);

		assertEquals(product.getId(), mockProductDetail.getProduct().getItem().getTcin());
		assertEquals(product.getName(), mockProductDetail.getProduct().getItem().getProduct_description().getTitle());
		assertEquals(product.getCurrent_price().getValue(), mockPrice.getValue());

	}
	
	
	@Test
	public void test_Product() {
		com.target.retail.model.Product product = getMockProduct(123);
		Price mockPrice = getMockPrice(123);

		when(priceRepository.findByProductId(anyInt())).thenReturn(mockPrice);
		when(priceRepository.save(any(Price.class))).thenReturn(mockPrice);

		com.target.retail.model.Product response = productServiceImpl.updateProduct(product);

		assertEquals(product.getId(), response.getId());
		assertEquals(product.getName(), response.getName());
		assertEquals(product.getCurrent_price().getValue(), response.getCurrent_price().getValue());

	}

	private Price getMockPrice(final Integer productId) {
		Price price = new Price();
		price.setCurrencyCode("USD");
		price.setId(1);
		price.setProductId(productId);
		price.setValue(new BigDecimal("12.32"));
		return price;

	}
	
	private com.target.retail.model.Product getMockProduct(final Integer productId) {
		com.target.retail.model.Product product = new com.target.retail.model.Product();
		Price mockPrice = getMockPrice(123);
		
		com.target.retail.model.Price price = new com.target.retail.model.Price();
		price.setCurrency_code(mockPrice.getCurrencyCode());
		price.setValue(new BigDecimal("12.11"));
		
		product.setCurrent_price(price);
		product.setId(productId);
		product.setName("Prodcut Name");
		
		return product;
	}

	private ProductDetail getMockProductResponse(final Integer productId) {
		ProductDetail productDetail = new ProductDetail();
		Product product = new Product();
		Item item = new Item();
		ProductDescription productDescription = new ProductDescription();
		productDescription.setTitle("Product Name");
		item.setProduct_description(productDescription);

		item.setTcin(productId);
		product.setItem(item);

		productDetail.setProduct(product);
		return productDetail;

	}
}
