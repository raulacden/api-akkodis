package com.rsp.akkodis.api.prices.domain.ports.out;

import java.math.BigInteger;
import java.util.Optional;

import com.rsp.akkodis.api.prices.domain.Product;

public interface ProductOutPort {

	Optional<Product> findById(BigInteger id);

}
