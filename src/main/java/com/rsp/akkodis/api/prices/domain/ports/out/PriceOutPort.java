package com.rsp.akkodis.api.prices.domain.ports.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;

public interface PriceOutPort {

	Optional<Price> findPrice(LocalDateTime applicationDate, Product product, Brand brand);

}
