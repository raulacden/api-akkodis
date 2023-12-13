package com.rsp.akkodis.api.prices.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.rsp.akkodis.api.prices.application.PriceController;
import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.DomainException;
import com.rsp.akkodis.api.prices.domain.Fee;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.domain.service.PriceService;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PriceService priceInService;

	@Test
	void obtainPriceByDateAndProductAndBrandEndpoint() throws Exception {

		var dateTime = LocalDateTime.parse("2020-06-14T10:00:00");
		var idProduct = BigInteger.ONE;
		var idBrand = BigInteger.ONE;

		when(priceInService.obtainPrice(dateTime, idProduct, idBrand)).thenReturn(
				new Price(BigInteger.ONE, new Brand(BigInteger.ONE, "Zara"), LocalDateTime.parse("2020-06-14T00:00:00"),
						LocalDateTime.parse("2020-12-31T23:59:59"), new Fee(BigInteger.ONE, "fee"),
						new Product(BigInteger.ONE, "Product 1"), 0, new BigDecimal("35.50"), "EUR"));

		mockMvc.perform(get("/prices").param("date", dateTime.toString()).param("idProduct", idProduct.toString())
				.param("idBrand", idBrand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.idProduct").value(1)).andExpect(jsonPath("$.idBrand").value(1))
				.andExpect(jsonPath("$.amount").value(35.50));

	}

	@Test
	void obtainPriceByDateAndProductAndBrandEndpoint_shouldDomainException() throws Exception {

		var dateTime = LocalDateTime.parse("2020-06-14T10:00:00");
		var idProduct = BigInteger.ONE;
		var idBrand = BigInteger.ONE;

		when(priceInService.obtainPrice(dateTime, idProduct, idBrand))
				.thenThrow(new DomainException("Informed product (" + idProduct + ") does not exists"));

		mockMvc.perform(get("/prices").param("date", dateTime.toString()).param("idProduct", idProduct.toString())
				.param("idBrand", idBrand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));

	}

}
