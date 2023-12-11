package com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface H2Mapper {

	H2Mapper INSTANCE = Mappers.getMapper(H2Mapper.class);

	Price fromEntity(PriceEntity priceEntity);

}
