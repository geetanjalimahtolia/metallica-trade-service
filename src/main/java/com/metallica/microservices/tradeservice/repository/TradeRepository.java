package com.metallica.microservices.tradeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metallica.microservices.tradeservice.bean.Trade;
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer>{
	
}
