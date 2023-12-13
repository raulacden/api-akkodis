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

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Fee;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2BrandRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2PriceRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2ProductRepository;

@DataJpaTest
class JpaTest {

	@Autowired
	SpringDatah2PriceRepository priceRepository;
	@Autowired
	SpringDatah2BrandRepository brandRepository;
	@Autowired
	SpringDatah2ProductRepository productRepository;

	private Price price;
	private Brand brand;
	private Product product;
	private Fee fee;

	@BeforeEach
	public void setUp() {
		brand = Brand.builder().id(BigInteger.ONE).name("Zara").build();
		fee = Fee.builder().id(BigInteger.ONE).name("Fee test").build();
		product = Product.builder().id(BigInteger.valueOf(35455)).name("Product test").build();
		price = Price.builder().id(BigInteger.ONE).brand(brand).startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
				.endDate(LocalDateTime.parse("2020-12-31T23:59:59")).fee(fee).product(product).priority(0)
				.amount(new BigDecimal("35.50")).curr("EUR").build();

	}

	@Test
	void saveBrandAndFindById() {
		brand = brandRepository.save(brand);
		var result = brandRepository.findById(brand.getId());
		assertTrue(result.isPresent());
		assertThat(brand).isEqualTo((result.get()));
	}

	@Test
	void saveProductAndFindById() {
		product = productRepository.save(product);
		var result = productRepository.findById(product.getId());
		assertTrue(result.isPresent());
		assertThat(product).isEqualTo((result.get()));
	}

	@Test
	void savePriceAndFindById() {
		brand = brandRepository.save(brand);
		product = productRepository.save(product);
		price = priceRepository.save(price);
		var result = priceRepository.findById(price.getId());
		assertTrue(result.isPresent());
		assertThat(price).isEqualTo((result.get()));
	}

}