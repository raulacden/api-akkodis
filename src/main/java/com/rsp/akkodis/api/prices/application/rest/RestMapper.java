package com.rsp.akkodis.api.prices.application.rest;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rsp.akkodis.api.prices.application.rest.dto.GetPriceResponse;
import com.rsp.akkodis.api.prices.domain.Price;

@Mapper(componentModel = "spring")
public interface RestMapper {

	RestMapper INSTANCE = Mappers.getMapper(RestMapper.class);

	GetPriceResponse fromDomain(Price price);

}
