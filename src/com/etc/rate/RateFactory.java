package com.etc.rate;

import com.etc.domain.VehicleType;

/**
 * A factory to decide the type rate applicable based on the type of vehicle.
 * 
 * @author kaushik
 */
public class RateFactory {

	public Rate getRate(VehicleType vehicleType) {
		switch (vehicleType) {
		case LCV:
		case LMV:
			return new FixedRate();
		case T2A:
		case T3A:
		case T4A:
		case T5A:
		case T6A:
		case T7A:
			return new DistanceRate();
		default:
			throw new RuntimeException("Invalid vehicle type!");
		}
	}

}
