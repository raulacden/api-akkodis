package com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2PriceRepository;

public class PriceAdapter implements PriceOutPort {

	@Autowired
	private SpringDatah2PriceRepository priceRepository;

	@Override
	public Optional<Price> findPrice(LocalDateTime applicationDate, Product product, Brand brand) {
		return priceRepository.findFirstByAndSort(applicationDate, H2Mapper.INSTANCE.fromDomain(product),
				H2Mapper.INSTANCE.fromDomain(brand)).map(H2Mapper.INSTANCE::fromEntity);
	}

}
