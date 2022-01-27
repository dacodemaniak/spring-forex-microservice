package com.aelion.forex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aelion.forex.models.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}
