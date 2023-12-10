package com.rsp.akkodis.api.prices.infrastructure.repository.h2.adapter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rsp.akkodis.api.prices.domain.Brand;
import com.rsp.akkodis.api.prices.domain.Price;
import com.rsp.akkodis.api.prices.domain.Product;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.BrandEntity;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.PriceEntity;
import com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface H2Mapper {

	H2Mapper INSTANCE = Mappers.getMapper(H2Mapper.class);

	Price fromEntity(PriceEntity priceEntity);

	Product fromEntity(ProductEntity productEntity);

	ProductEntity fromDomain(Product produc);

	Brand fromEntity(BrandEntity brandEntity);

	BrandEntity fromDomain(Brand brand);

}
