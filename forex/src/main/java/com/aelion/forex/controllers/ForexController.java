package com.aelion.forex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.forex.models.ExchangeValue;
import com.aelion.forex.repositories.ExchangeValueRepository;

@RestController
@RequestMapping("/api/forex")
public class ForexController {
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/{from}/{to}")
	public ExchangeValue retrieveExchangeValue (
			@PathVariable String from,
			@PathVariable String to
	) {
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(
				Integer.parseInt(environment.getProperty("local.server.port"))
		);
		
		return exchangeValue;
	}
	
}
