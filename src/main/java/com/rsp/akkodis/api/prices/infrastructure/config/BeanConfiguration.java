package com.rsp.akkodis.api.prices.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rsp.akkodis.api.prices.domain.ports.in.PriceInPort;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;
import com.rsp.akkodis.api.prices.domain.service.PriceInService;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter.PriceAdapter;

@Configuration
public class BeanConfiguration {

	@Bean
	PriceOutPort priceOutPort() {
		return new PriceAdapter();
	}

	@Bean
	PriceInPort priceInPort() {
		return new PriceInService(priceOutPort());
	}

}
