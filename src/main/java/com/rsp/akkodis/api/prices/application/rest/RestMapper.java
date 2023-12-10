package com.rsp.akkodis.api.prices.application.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.rsp.akkodis.api.prices.application.rest.dto.GetPriceResponse;
import com.rsp.akkodis.api.prices.domain.Price;

@Mapper(componentModel = "spring")
public interface RestMapper {

	RestMapper INSTANCE = Mappers.getMapper(RestMapper.class);

	@Mapping(source = "brand.id", target = "idBrand")
	@Mapping(source = "product.id", target = "idProduct")
	@Mapping(source = "fee.id", target = "idFee")
	GetPriceResponse fromDomain(Price price);

}
