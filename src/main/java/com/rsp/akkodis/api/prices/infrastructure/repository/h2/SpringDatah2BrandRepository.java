package com.rsp.akkodis.api.prices.infrastructure.repository.h2;

import java.math.BigInteger;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.BrandEntity;

@Repository
public interface SpringDatah2BrandRepository extends ListCrudRepository<BrandEntity, BigInteger> {

}
