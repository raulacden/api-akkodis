package com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.ports.out.BrandOutPort;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2BrandRepository;

public class BrandAdapter implements BrandOutPort {

	@Autowired
	private SpringDatah2BrandRepository brandRepository;

	@Override
	public Optional<Brand> findById(BigInteger id) {
		return brandRepository.findById(id).map(H2Mapper.INSTANCE::fromEntity);
	}

}
