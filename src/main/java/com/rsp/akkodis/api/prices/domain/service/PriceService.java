package com.rsp.akkodis.api.prices.domain.service;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.akkodis.api.prices.domain.DomainException;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2BrandRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2PriceRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2ProductRepository;

@Service
public class PriceService {

	@Autowired
	private SpringDatah2PriceRepository springDatah2PriceRepository;

	@Autowired
	private SpringDatah2BrandRepository springDatah2BrandRepository;

	@Autowired
	private SpringDatah2ProductRepository springDatah2ProductRepository;

	public Price obtainPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand) {

		var brand = springDatah2BrandRepository.findById(idBrand)
				.orElseThrow(() -> new DomainException("Informed brand (" + idBrand + ") does not exists"));

		var product = springDatah2ProductRepository.findById(idProduct)
				.orElseThrow(() -> new DomainException("Informed product (" + idProduct + ") does not exists"));

		return springDatah2PriceRepository.findFirstByAndSort(applicationDate, product, brand)
				.orElseThrow(() -> new DomainException("There is no price registered for the input data"));
	}
}
