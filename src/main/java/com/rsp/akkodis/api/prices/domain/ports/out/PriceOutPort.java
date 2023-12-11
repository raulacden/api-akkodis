package com.rsp.akkodis.api.prices.domain.ports.out;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import com.rsp.akkodis.api.prices.domain.Price;

public interface PriceOutPort {

	Optional<Price> findPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand);

}
