package com.rsp.akkodis.api.prices.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rsp.akkodis.api.prices.ApiApplication;
import com.rsp.akkodis.api.prices.domain.service.PriceService;

@SpringBootTest
class IntegrationTests {

	@Autowired
	private PriceService priceService;

	private static BigInteger idProduct;
	private static BigInteger idBrand;

	@BeforeAll
	public static void setUp() {
		idProduct = BigInteger.valueOf(35455);
		idBrand = BigInteger.valueOf(1);
	}

	@Test
	void contextLoads() {
		assertThat(priceService).isNotNull();
	}

	@Test
	void applicationContextTest() {
		ApiApplication.main(new String[] {});
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest1() {
		var ldt = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

		var price = priceService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), price.getStartDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), price.getEndDate());
		assertEquals(BigInteger.ONE, price.getFee().getId());
		assertEquals(BigInteger.valueOf(35455), price.getProduct().getId());
		assertEquals(0, price.getPriority());
		assertEquals(0, BigDecimal.valueOf(35.50).compareTo(price.getAmount()));
		assertEquals("EUR", price.getCurr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest2() {
		var ldt = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

		var price = priceService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 14, 15, 0, 0), price.getStartDate());
		assertEquals(LocalDateTime.of(2020, 6, 14, 18, 30, 0), price.getEndDate());
		assertEquals(BigInteger.TWO, price.getFee().getId());
		assertEquals(BigInteger.valueOf(35455), price.getProduct().getId());
		assertEquals(1, price.getPriority());
		assertEquals(0, BigDecimal.valueOf(25.45).compareTo(price.getAmount()));
		assertEquals("EUR", price.getCurr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest3() {
		var ldt = LocalDateTime.of(2020, 6, 14, 21, 0, 0);

		var price = priceService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), price.getStartDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), price.getEndDate());
		assertEquals(BigInteger.ONE, price.getFee().getId());
		assertEquals(BigInteger.valueOf(35455), price.getProduct().getId());
		assertEquals(0, price.getPriority());
		assertEquals(0, BigDecimal.valueOf(35.50).compareTo(price.getAmount()));
		assertEquals("EUR", price.getCurr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest4() {
		var ldt = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

		var price = priceService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 15, 00, 0, 0), price.getStartDate());
		assertEquals(LocalDateTime.of(2020, 6, 15, 11, 0, 0), price.getEndDate());
		assertEquals(BigInteger.valueOf(3), price.getFee().getId());
		assertEquals(BigInteger.valueOf(35455), price.getProduct().getId());
		assertEquals(1, price.getPriority());
		assertEquals(0, BigDecimal.valueOf(30.50).compareTo(price.getAmount()));
		assertEquals("EUR", price.getCurr());
	}

	@Test
	void givenDataBaseLoaded_IntegrationTest5() {
		var ldt = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

		var price = priceService.obtainPrice(ldt, idProduct, idBrand);

		assertEquals(LocalDateTime.of(2020, 6, 15, 16, 0, 0), price.getStartDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), price.getEndDate());
		assertEquals(BigInteger.valueOf(4), price.getFee().getId());
		assertEquals(BigInteger.valueOf(35455), price.getProduct().getId());
		assertEquals(1, price.getPriority());
		assertEquals(0, BigDecimal.valueOf(38.95).compareTo(price.getAmount()));
		assertEquals("EUR", price.getCurr());
	}

}
