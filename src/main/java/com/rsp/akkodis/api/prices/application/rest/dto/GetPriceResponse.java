package com.rsp.akkodis.api.prices.application.rest.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record GetPriceResponse(BigInteger idProduct, BigInteger idBrand, BigInteger idFee, LocalDateTime startDate,
		LocalDateTime endDate, BigDecimal amount, String curr) {

}
