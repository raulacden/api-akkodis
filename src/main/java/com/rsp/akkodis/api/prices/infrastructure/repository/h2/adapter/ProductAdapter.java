package com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.domain.ports.out.ProductOutPort;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2ProductRepository;

public class ProductAdapter implements ProductOutPort {

	@Autowired
	private SpringDatah2ProductRepository productRepository;

	@Override
	public Optional<Product> findById(BigInteger id) {
		return productRepository.findById(id).map(H2Mapper.INSTANCE::fromEntity);
	}

}
