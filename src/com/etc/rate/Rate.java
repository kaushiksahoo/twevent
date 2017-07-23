package com.etc.rate;

import com.etc.domain.Gate;
import com.etc.domain.VehicleType;

/**
 * Rate information.
 * 
 * @author kaushik
 */
public interface Rate {
	
	/**
	 * Get the charges for a ride of the given vehicle type. 
	 * @param vehicleType the {@link VehicleType}
	 * @param entryGate the entry gate
	 * @param exitGate the exit gate
	 * @return calculated charges
	 */
	public float getCharges(VehicleType vehicleType, Gate entryGate, Gate exitGate);

}
