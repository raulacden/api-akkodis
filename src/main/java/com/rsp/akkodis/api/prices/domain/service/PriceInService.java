package com.rsp.akkodis.api.prices.domain.service;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.domain.error.DomainException;
import com.rsp.akkodis.api.prices.domain.ports.in.PriceInPort;
import com.rsp.akkodis.api.prices.domain.ports.out.BrandOutPort;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;
import com.rsp.akkodis.api.prices.domain.ports.out.ProductOutPort;

public class PriceInService implements PriceInPort {

	private PriceOutPort priceOutPort;
	private BrandOutPort brandOutPort;
	private ProductOutPort productOutPort;

	public PriceInService(PriceOutPort priceOutPort, BrandOutPort brandOutPort, ProductOutPort productOutPort) {
		this.priceOutPort = priceOutPort;
		this.brandOutPort = brandOutPort;
		this.productOutPort = productOutPort;
	}

	@Override
	public Price obtainPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand) {

		Brand brand = brandOutPort.findById(idBrand)
				.orElseThrow(() -> new DomainException("Informed brand (" + idBrand + ") does not exists"));

		Product product = productOutPort.findById(idProduct)
				.orElseThrow(() -> new DomainException("Informed product (" + idProduct + ") does not exists"));

		return priceOutPort.findPrice(applicationDate, product, brand)
				.orElseThrow(() -> new DomainException("There is no price registered for the input data"));
	}
}
