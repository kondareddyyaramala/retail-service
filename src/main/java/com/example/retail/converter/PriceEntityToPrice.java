package com.example.retail.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.retail.entity.Price;

@Component
public class PriceEntityToPrice implements Converter<Price, com.example.retail.model.Price> {

	@Override
	public com.example.retail.model.Price convert(Price source) {
		com.example.retail.model.Price price = new com.example.retail.model.Price();
		price.setCurrency_code(source.getCurrencyCode());
		price.setValue(source.getValue());
		return price;
	}

}
