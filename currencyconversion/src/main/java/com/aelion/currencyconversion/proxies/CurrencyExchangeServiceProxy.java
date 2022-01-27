package com.aelion.currencyconversion.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aelion.currencyconversion.dto.CurrencyConversionBean;

@FeignClient(name="forex-service")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/api/forex/{from}/{to}")
	public CurrencyConversionBean retrieveExchangeValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to
	);
}
