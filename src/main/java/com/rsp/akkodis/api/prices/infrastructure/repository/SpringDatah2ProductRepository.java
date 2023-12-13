package com.rsp.akkodis.api.prices.infrastructure.repository;

import java.math.BigInteger;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.akkodis.api.prices.domain.Product;

@Repository
public interface SpringDatah2ProductRepository extends ListCrudRepository<Product, BigInteger> {

}
