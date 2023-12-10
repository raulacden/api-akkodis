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

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Fee;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.domain.error.DomainException;
import com.rsp.akkodis.api.prices.domain.ports.out.BrandOutPort;
import com.rsp.akkodis.api.prices.domain.ports.out.PriceOutPort;
import com.rsp.akkodis.api.prices.domain.ports.out.ProductOutPort;
import com.rsp.akkodis.api.prices.domain.service.PriceInService;

class PriceInServiceTest {

	private PriceOutPort priceOutPort;
	private BrandOutPort brandOutPort;
	private ProductOutPort productOutPort;
	private PriceInService priceInService;

	@BeforeEach
	void setUp() {
		priceOutPort = mock(PriceOutPort.class);
		brandOutPort = mock(BrandOutPort.class);
		productOutPort = mock(ProductOutPort.class);
		priceInService = new PriceInService(priceOutPort, brandOutPort, productOutPort);
	}

	@Test
	void shouldNotObtainPrice_whenBrandNotExist_thenTrhowException() {
		var idBrand = BigInteger.TWO;
		var idProduct = BigInteger.TWO;
		var date = LocalDateTime.now();

		when(brandOutPort.findById(idBrand)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceInService.obtainPrice(date, idProduct, idBrand);
		});

	}

	@Test
	void shouldNotObtainPrice_whenProductNotExist_thenTrhowException() {
		var idBrand = BigInteger.TWO;
		var idProduct = BigInteger.TWO;
		var date = LocalDateTime.now();

		when(productOutPort.findById(idProduct)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceInService.obtainPrice(date, idProduct, idBrand);
		});

	}

	@Test
	void shouldObtainPrice_whenBrandAndProductExist() {
		var fee = new Fee(BigInteger.ONE, "Fee Test");
		var brand = new Brand(BigInteger.ONE, "Zara");
		var product = new Product(BigInteger.ONE, "Product Test");
		var date = LocalDateTime.now();

		when(brandOutPort.findById(brand.id())).thenReturn(Optional.of(brand));
		when(productOutPort.findById(product.id())).thenReturn(Optional.of(product));
		when(priceOutPort.findPrice(date, product, brand)).thenReturn(Optional.of(new Price(BigInteger.ONE, brand,
				LocalDateTime.MIN, LocalDateTime.MAX, fee, product, 0, BigDecimal.TEN, "EUR")));

		var priceObtained = priceInService.obtainPrice(date, product.id(), brand.id());

		assertEquals(BigInteger.ONE, priceObtained.product().id());
		assertEquals(BigInteger.ONE, priceObtained.brand().id());
		assertEquals(LocalDateTime.MAX, priceObtained.endDate());

	}

}
