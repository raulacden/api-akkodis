package com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2PriceRepository;

public class PriceAdapter implements PriceOutPort {

	@Autowired
	private SpringDatah2PriceRepository priceRepository;

	@Override
	public Optional<Price> findPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand) {
		return priceRepository.findFirstByAndSort(applicationDate, idProduct, idBrand)
				.map(H2Mapper.INSTANCE::fromEntity);
	}

}
