package com.metallica.microservices.tradeservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublishService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

   	public void sendTradeToQueue(int tradeId) {
   		rabbitTemplate.convertAndSend("metallica-trade-exchange", "trades-register", "{\"tradeId\":" + tradeId + ",\"status\":\"INITIATED\"}");
   	}

}