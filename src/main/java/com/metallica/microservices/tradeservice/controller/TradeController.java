package com.metallica.microservices.tradeservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.metallica.microservices.tradeservice.MarketDataServiceProxy;
import com.metallica.microservices.tradeservice.bean.Trade;
import com.metallica.microservices.tradeservice.repository.TradeRepository;
import com.metallica.microservices.tradeservice.RabbitMQPublishService;

@RestController
public class TradeController {
	@Autowired
	private RabbitMQPublishService publisher;
	
	@Autowired
	private MarketDataServiceProxy proxy;
	
	@Autowired
	private TradeRepository repository;
	
	@GetMapping("/trades")
	public List<Trade> retrieveAllTrades()
	{
		return repository.findAll();
	}
	
	@PostMapping("/trades")
	public ResponseEntity<Trade> buyTrade(@RequestBody Trade trade)
	{
		trade.setCommodity("AL");
		trade.setCounterParty("Lorem");
		trade.setLocation("BA");
		
		/*Map<String,String> uriVariables=new HashMap<>();
		uriVariables.put("commodity", trade.getCommodity());
		ResponseEntity<Trade> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/market-price/for/{commodity}", Trade.class, uriVariables);
		Trade response=responseEntity.getBody();
		trade.setPrice(response.getPrice());*/
		
		Optional<Trade> response=proxy.retrieveMarketPrice(trade.getCommodity());
		Trade tradeResponse=response.get();
		trade.setPrice(tradeResponse.getPrice());
		Trade savedTrade=repository.save(trade);
		publisher.sendTradeToQueue(savedTrade.getId());
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTrade.getId()).toUri();
		return ResponseEntity.created(location).build(); 
	}
}
