package com.rsp.akkodis.api.prices.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2PriceRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.PriceEntity;

@DataJpaTest
class JpaTest {

	@Autowired
	SpringDatah2PriceRepository springDatah2PriceRepository;

	private PriceEntity priceEntity;

	@BeforeEach
	public void setUp() {
		priceEntity = PriceEntity.builder().id(BigInteger.ONE).idBrand(BigInteger.ONE)
				.startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
				.endDate(LocalDateTime.parse("2020-12-31T23:59:59")).idFee(BigInteger.ONE)
				.idProduct(BigInteger.valueOf(35455)).priority(0).amount(new BigDecimal("35.50")).curr("EUR").build();

	}

	@Test
	void savePriceEntityAndFindById() {
		priceEntity = springDatah2PriceRepository.save(priceEntity);
		var result = springDatah2PriceRepository.findById(priceEntity.getId());
		assertTrue(result.isPresent());
		assertThat(priceEntity).isEqualTo((result.get()));
	}

}
