package com.rsp.akkodis.api.prices.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record Price(BigInteger id, BigInteger idBrand, LocalDateTime startDate, LocalDateTime endDate, BigInteger idFee,
		BigInteger idProduct, int priority, BigDecimal amount, String curr) {
}
