package com.rsp.akkodis.api.prices.infrastructure.repository.h2.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "fees")
@NoArgsConstructor
@AllArgsConstructor
public class FeeEntity {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int8", name = "id", nullable = false, updatable = false, unique = true)
	private BigInteger id;

	@Column(columnDefinition = "varchar(150)", name = "name", nullable = false, updatable = true)
	private String name;

}
