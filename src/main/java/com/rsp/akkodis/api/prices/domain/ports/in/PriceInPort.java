package com.rsp.akkodis.api.prices.domain.ports.in;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.rsp.akkodis.api.prices.domain.Price;

public interface PriceInPort {

	Price obtainPrice(LocalDateTime applicationDate, BigInteger idProduct, BigInteger idBrand);
}
