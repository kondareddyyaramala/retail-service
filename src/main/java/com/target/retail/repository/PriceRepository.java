package com.target.retail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.target.retail.entity.Price;

@Repository
public interface PriceRepository extends MongoRepository<Price, Integer> {

	Price findByProductId(Integer productId);
}
