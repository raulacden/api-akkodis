package com.rsp.akkodis.api.prices.infrastructure.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;

@Repository
public interface SpringDatah2PriceRepository extends ListCrudRepository<Price, BigInteger> {

	@Query("select p from Price p where ?1 between p.startDate and p.endDate and p.product = ?2 and p.brand = ?3 order by priority DESC Limit 1")
	Optional<Price> findFirstByAndSort(LocalDateTime date, Product product, Brand brand);

}
