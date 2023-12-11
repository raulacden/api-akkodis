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

import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2BrandRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2PriceRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.SpringDatah2ProductRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.BrandEntity;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.FeeEntity;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.PriceEntity;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.ProductEntity;

@DataJpaTest
class JpaTest {

	@Autowired
	SpringDatah2PriceRepository priceRepository;
	@Autowired
	SpringDatah2BrandRepository brandRepository;
	@Autowired
	SpringDatah2ProductRepository productRepository;

	private PriceEntity priceEntity;
	private BrandEntity brandEntity;
	private ProductEntity productEntity;
	private FeeEntity feeEntity;

	@BeforeEach
	public void setUp() {
		brandEntity = BrandEntity.builder().id(BigInteger.ONE).name("Zara").build();
		feeEntity = FeeEntity.builder().id(BigInteger.ONE).name("Fee test").build();
		productEntity = ProductEntity.builder().id(BigInteger.valueOf(35455)).name("Product test").build();
		priceEntity = PriceEntity.builder().id(BigInteger.ONE).brand(brandEntity)
				.startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
				.endDate(LocalDateTime.parse("2020-12-31T23:59:59")).fee(feeEntity).product(productEntity).priority(0)
				.amount(new BigDecimal("35.50")).curr("EUR").build();

	}

	@Test
	void saveBrandEntityAndFindById() {
		brandEntity = brandRepository.save(brandEntity);
		var result = brandRepository.findById(brandEntity.getId());
		assertTrue(result.isPresent());
		assertThat(brandEntity).isEqualTo((result.get()));
	}

	@Test
	void saveProductEntityAndFindById() {
		productEntity = productRepository.save(productEntity);
		var result = productRepository.findById(productEntity.getId());
		assertTrue(result.isPresent());
		assertThat(productEntity).isEqualTo((result.get()));
	}

	@Test
	void savePriceEntityAndFindById() {
		brandEntity = brandRepository.save(brandEntity);
		productEntity = productRepository.save(productEntity);
		priceEntity = priceRepository.save(priceEntity);
		var result = priceRepository.findById(priceEntity.getId());
		assertTrue(result.isPresent());
		assertThat(priceEntity).isEqualTo((result.get()));
	}

}