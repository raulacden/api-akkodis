package com.rsp.akkodis.api.prices.domain.service;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.error.DomainException;
import com.rsp.akkodis.api.prices.domain.ports.in.PriceInPort;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;

public class PriceInService implements PriceInPort {

	private PriceOutPort priceOutPort;

	public PriceInService(PriceOutPort priceOutPort) {
		this.priceOutPort = priceOutPort;
	}

	@Override
	public Price obtainPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand) {

		return priceOutPort.findPrice(applicationDate, idProduct, idBrand)
				.orElseThrow(() -> new DomainException("There is no price registered for the input data"));
	}
}
