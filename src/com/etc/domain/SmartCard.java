package com.etc.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Smart card info
 * 
 * @author kaushik
 */
public class SmartCard {

	private static final int DEFAULT_VALIDITY_YEARS = 3;

	private String id;
	private String name;
	private String vehicleNumber;

	private VehicleType vehicleType;

	private Date validity;

	private float balance;

	public SmartCard(String name, String vehicleNumber, VehicleType vehicleType) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;

		this.validity = Date.from(ZonedDateTime.now().plusYears(DEFAULT_VALIDITY_YEARS).toInstant());
	}

	public Date getValidity() {
		return validity;
	}

	/**
	 * Extend the validity of card by the provided number of years.
	 * 
	 * @param years
	 *            the years to extend validity
	 * @return the new validity
	 */
	public Date extendValidity(int years) {
		this.validity = Date.from(validity.toInstant().atZone(ZoneId.systemDefault()).plusYears(years).toInstant());
		return validity;
	}

	/**
	 * Disables the card by updating the validity to now. So the card is not
	 * valid from the next instant.
	 */
	public void disableCard() {
		this.validity = new Date();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public float getBalance() {
		return balance;
	}

	/**
	 * Add balance to the smart card.
	 * 
	 * @param amount
	 *            the amount to be added
	 * @return the updated amount
	 */
	public float addBalance(float amount) {
		balance += amount;
		return balance;
	}

	/**
	 * Deduct balance from the card.
	 * 
	 * @param amount
	 *            the amount to be deducted
	 * @return the updated amount
	 */
	public float deductBalance(float amount) {
		balance -= amount;
		return balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((validity == null) ? 0 : validity.hashCode());
		result = prime * result + ((vehicleNumber == null) ? 0 : vehicleNumber.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartCard other = (SmartCard) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (validity == null) {
			if (other.validity != null)
				return false;
		} else if (!validity.equals(other.validity))
			return false;
		if (vehicleNumber == null) {
			if (other.vehicleNumber != null)
				return false;
		} else if (!vehicleNumber.equals(other.vehicleNumber))
			return false;
		if (vehicleType != other.vehicleType)
			return false;
		return true;
	}
}
