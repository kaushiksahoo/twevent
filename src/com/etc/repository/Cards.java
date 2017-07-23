package com.etc.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.etc.domain.SmartCard;
import com.etc.domain.VehicleType;

/**
 * Simulates a `Card` db Table
 * 
 * @author kaushik
 */
public class Cards {
	
	private Map<String, SmartCard> cards;
	
	public Cards() {
		cards = new HashMap<>();
	}
	
	/**
	 * Register a new card with initial balance.
	 * @param vehicleNumber the vehicle number
	 * @param vehicleType type of vehicle
	 * @param userName the card owner name
	 * @param rechargeAmount
	 */
	public void registerCard(String vehicleNumber, VehicleType vehicleType, String userName, float rechargeAmount) {
		SmartCard card = new SmartCard(userName, vehicleNumber, vehicleType);
		card.addBalance(rechargeAmount);
		
		cards.put(vehicleNumber, card);
	}
	
	/**
	 * Get smart card associated with a vehicle number.
	 * @param vehicleNumber the vehicle number
	 * @return {@link SmartCard} details
	 */
	public SmartCard getCard(String vehicleNumber) {
		return cards.get(vehicleNumber);
	}
	
	/**
	 * Validate a smart card.
	 * @param card the card to validate
	 * @return true, if card is valid and has balance
	 */
	public boolean isValid(SmartCard card) {
		SmartCard dbCard = cards.get(card.getVehicleNumber());
		return (card.equals(dbCard)
				&& dbCard.getValidity().after(new Date())
				&& dbCard.getBalance() > 0);
	}
	
	/**
	 * Recharge the card
	 * @param card the card to recharge
	 * @param amount the amount to be added
	 * @return updated balance
	 */
	public float recharge(SmartCard card, float amount) {
		return card.addBalance(amount);
	}
}
