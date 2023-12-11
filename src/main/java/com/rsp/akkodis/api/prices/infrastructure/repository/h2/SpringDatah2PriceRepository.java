package com.rsp.akkodis.api.prices.infrastructure.repository.h2;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.PriceEntity;

@Repository
public interface SpringDatah2PriceRepository extends ListCrudRepository<PriceEntity, BigInteger> {

	@Query(nativeQuery = true, value = "select * from prices p where ?1 between p.start_date and p.end_date and p.product_id = ?2 and p.brand_id = ?3 order by priority DESC Limit 1")
	Optional<PriceEntity> findFirstByAndSort(LocalDateTime date, BigInteger idProduct, BigInteger idBrand);

}
