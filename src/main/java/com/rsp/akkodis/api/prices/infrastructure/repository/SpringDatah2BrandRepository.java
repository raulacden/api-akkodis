package com.rsp.akkodis.api.prices.infrastructure.repository;

import java.math.BigInteger;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.akkodis.api.prices.domain.Brand;

@Repository
public interface SpringDatah2BrandRepository extends ListCrudRepository<Brand, BigInteger> {

}
