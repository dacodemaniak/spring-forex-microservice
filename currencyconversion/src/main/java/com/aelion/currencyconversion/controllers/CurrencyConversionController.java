package com.aelion.currencyconversion.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aelion.currencyconversion.dto.CurrencyConversionBean;
import com.aelion.currencyconversion.proxies.CurrencyExchangeServiceProxy;

@RestController
@RequestMapping("/api/currency/converter")
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping("/{from}/{to}/{quantity}")
	public CurrencyConversionBean feignConvert(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
	) {
		

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		logger.info("{}", response);
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
		        quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
}
