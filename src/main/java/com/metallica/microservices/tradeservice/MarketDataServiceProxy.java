package com.metallica.microservices.tradeservice;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.metallica.microservices.tradeservice.bean.Trade;

//@FeignClient(name="market-data-service",url="localhost:8000")
//@FeignClient(name="market-data-service")

@FeignClient(name="netflix-zuul-api-gateway")
public interface MarketDataServiceProxy {
	
	//@GetMapping("/market-price/for/{commodity}")
	@GetMapping("/market-data-service/market-price/for/{commodity}")
	public Optional<Trade> retrieveMarketPrice(@PathVariable String commodity);
	
}
