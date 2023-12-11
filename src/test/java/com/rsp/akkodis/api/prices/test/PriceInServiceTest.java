package com.rsp.akkodis.api.prices.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.error.DomainException;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;
import com.rsp.akkodis.api.prices.domain.service.PriceInService;

class PriceInServiceTest {

	private PriceOutPort priceOutPort;
	private PriceInService priceInService;

	@BeforeEach
	void setUp() {
		priceOutPort = mock(PriceOutPort.class);
		priceInService = new PriceInService(priceOutPort);
	}

	@Test
	void shouldObtainPrice_whenBrandAndProductExistAndDateIsBetweenDates_ThenThrowException() {
		var inputDate = LocalDateTime.parse("2020-06-20T10:00:00");

		when(priceOutPort.findPrice(inputDate, BigInteger.ONE, BigInteger.ONE)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceInService.obtainPrice(inputDate, BigInteger.ONE, BigInteger.ONE);
		});

	}

	@Test
	void shouldObtainPrice_whenBrandAndProductExistAndDateIsBetweenDates() {
		var startDate = LocalDateTime.parse("2020-06-14T00:00:00");
		var endDate = LocalDateTime.parse("2020-06-16T10:00:00");
		var inputDate = LocalDateTime.parse("2020-06-15T10:00:00");

		when(priceOutPort.findPrice(inputDate, BigInteger.ONE, BigInteger.ONE))
				.thenReturn(Optional.of(new Price(BigInteger.ONE, BigInteger.ONE, startDate, endDate, BigInteger.ONE,
						BigInteger.ONE, 0, BigDecimal.TEN, "EUR")));

		var priceObtained = priceInService.obtainPrice(inputDate, BigInteger.ONE, BigInteger.ONE);

		assertEquals(BigInteger.ONE, priceObtained.idBrand());
		assertEquals(BigInteger.ONE, priceObtained.idProduct());
		assertEquals(endDate, priceObtained.endDate());

	}

}
