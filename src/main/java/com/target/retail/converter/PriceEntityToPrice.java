package com.target.retail.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.target.retail.entity.Price;

@Component
public class PriceEntityToPrice implements Converter<Price, com.target.retail.model.Price> {

	@Override
	public com.target.retail.model.Price convert(Price source) {
		com.target.retail.model.Price price = new com.target.retail.model.Price();
		price.setCurrency_code(source.getCurrencyCode());
		price.setValue(source.getValue());
		return price;
	}

}
