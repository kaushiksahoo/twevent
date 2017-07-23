package com.etc.repository;

import java.util.HashMap;
import java.util.Map;

import com.etc.domain.Gate;
import com.etc.domain.SmartCard;
import com.etc.exception.RideException;
import com.etc.rate.RateFactory;

/**
 * Rides manager, also simulates the `Ride` db Table
 * 
 * @author kaushik
 *
 */
public class Rides {

	private Map<String, Gate> rides;
	
	private Cards cards;

	public Rides(Cards cards) {
		rides = new HashMap<>();
		this.cards = cards;
	}

	/**
	 * Register entry of a vehicle
	 * @param vehicleNumber the vehicle number
	 * @param entryGate the entry gate
	 * @throws RideException thrown if entry validation fails
	 */
	public void entry(String vehicleNumber, Gate entryGate) throws RideException {
		if (rides.containsKey(vehicleNumber))
			throw new RideException("Exit brfore entering again!");
		
		if (!cards.isValid(cards.getCard(vehicleNumber)))
			throw new RideException("Invalid Card!");

		rides.put(vehicleNumber, entryGate);
	}

	/**
	 * Register exit of vehicle and deduct charges.
	 * @param vehicleNumber the vehicle number
	 * @param exitGate the exit gate
	 * @return the balance in the card after charge deduction
	 * @throws RideException thrown if exit validation fails
	 */
	public float exit(String vehicleNumber, Gate exitGate) throws RideException {
		SmartCard card = cards.getCard(vehicleNumber);
		if (card == null)
			throw new RideException("Invalid Card!");
		
		if (!rides.containsKey(card.getVehicleNumber()))
			throw new RideException("Not In. Collect Fine!");

		Gate entryGate = rides.get(card.getVehicleNumber());
		float charges = new RateFactory().getRate(card.getVehicleType())
				.getCharges(card.getVehicleType(), entryGate, exitGate);
		card.deductBalance(charges);

		System.out.println("Commuter Name: " + card.getName() + ", balance deducted: " + charges
				+ ", remaining balance: " + card.getBalance());

		return charges;
	}
}
