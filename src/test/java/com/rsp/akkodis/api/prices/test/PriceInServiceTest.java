package com.rsp.akkodis.api.prices.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.DomainException;
import com.rsp.akkodis.api.prices.domain.Fee;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.domain.service.PriceService;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2BrandRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2PriceRepository;
import com.rsp.akkodis.api.prices.infrastructure.repository.SpringDatah2ProductRepository;

@SpringBootTest
class PriceInServiceTest {

	@Autowired
	private PriceService priceService;

	@MockBean
	private SpringDatah2BrandRepository springDatah2BrandRepository;

	@MockBean
	private SpringDatah2ProductRepository springDatah2ProductRepository;

	@MockBean
	private SpringDatah2PriceRepository springDatah2PriceRepository;

	@Test
	void shouldNotObtainPrice_whenBrandNotExist_thenThrowException() {
		var idBrand = BigInteger.TWO;
		var idProduct = BigInteger.TWO;
		var date = LocalDateTime.now();

		when(springDatah2BrandRepository.findById(idBrand)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceService.obtainPrice(date, idProduct, idBrand);
		});

	}

	@Test
	void shouldNotObtainPrice_whenProductNotExist_thenThrowException() {
		var idBrand = BigInteger.TWO;
		var idProduct = BigInteger.TWO;
		var date = LocalDateTime.now();

		when(springDatah2ProductRepository.findById(idProduct)).thenReturn(Optional.empty());

		assertThrows(DomainException.class, () -> {
			priceService.obtainPrice(date, idProduct, idBrand);
		});

	}

	@Test
	void shouldObtainPrice_whenBrandAndProductExist() {
		var fee = new Fee(BigInteger.ONE, "Fee Test");
		var brand = new Brand(BigInteger.ONE, "Zara");
		var product = new Product(BigInteger.ONE, "Product Test");
		var date = LocalDateTime.now();

		when(springDatah2BrandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));
		when(springDatah2ProductRepository.findById(product.getId())).thenReturn(Optional.of(product));
		when(springDatah2PriceRepository.findFirstByAndSort(date, product, brand))
				.thenReturn(Optional.of(new Price(BigInteger.ONE, brand, LocalDateTime.MIN, LocalDateTime.MAX, fee,
						product, 0, BigDecimal.TEN, "EUR")));

		var priceObtained = priceService.obtainPrice(date, product.getId(), brand.getId());

		assertEquals(BigInteger.ONE, priceObtained.getProduct().getId());
		assertEquals(BigInteger.ONE, priceObtained.getBrand().getId());
		assertEquals(LocalDateTime.MAX, priceObtained.getEndDate());

	}

}
