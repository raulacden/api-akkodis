package com.rsp.akkodis.api.prices.domain.ports.out;

import java.math.BigInteger;
import java.util.Optional;

import com.rsp.akkodis.api.prices.domain.Brand;

public interface BrandOutPort {

	Optional<Brand> findById(BigInteger id);

}
