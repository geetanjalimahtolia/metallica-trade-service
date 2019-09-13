package com.metallica.microservices.tradeservice.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Trade {

	@Id
	@GeneratedValue
	private int id;
	private int quantity;
	private Date tradeDate;
	@Enumerated(EnumType.STRING)
	private Side side;
	@Enumerated(EnumType.STRING)
	private TradeStatus status;
	private String commodity;
	private String counterParty;
	private String location;
	private double price;
	
	protected Trade()
	{
		
	}

	public Trade(int id, int quantity, Date tradeDate, Side side, TradeStatus status, String commodity,
			String counterParty, String location, double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.tradeDate = tradeDate;
		this.side = side;
		this.status = status;
		this.commodity = commodity;
		this.counterParty = counterParty;
		this.location = location;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", quantity=" + quantity + ", tradeDate=" + tradeDate + ", side=" + side
				+ ", status=" + status + ", commodity=" + commodity + ", counterParty=" + counterParty + ", location="
				+ location + ", price=" + price + "]";
	}

}
