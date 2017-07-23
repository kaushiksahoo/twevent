/**
 * 
 */
package com.etc.rate;

import com.etc.domain.Gate;
import com.etc.domain.VehicleType;
import com.etc.repository.Config;

/**
 * Distance based rate computation.
 * 
 * @author kaushik
 *
 */
public class DistanceRate implements Rate {

	/* (non-Javadoc)
	 * @see com.etc.rate.Rate#getCharges(com.etc.domain.VehicleType, com.etc.domain.Gate, com.etc.domain.Gate)
	 */
	@Override
	public float getCharges(VehicleType vehicleType, Gate entryGate, Gate exitGate) {
		int distance = getDistance(entryGate, exitGate);
		
		return Config.distanceConfig.get(vehicleType).baseChanrge + 
				(Config.distanceConfig.get(vehicleType).charge * distance);
	}

	/**
	 * Get total distance traveled.
	 * @param entryGate the entry gate
	 * @param exitGate the exit gate
	 * @return total distance between two gates
	 */
	private int getDistance(Gate entryGate, Gate exitGate) {
		int startGateId = entryGate.getId();
		int endGateId = exitGate.getId();
		
		int distance = 0;

		int currentGateId = startGateId;
		while (currentGateId != endGateId) {
			distance += Config.gateConfig.get(currentGateId).distance;
			currentGateId = Config.gateConfig.get(currentGateId).exId;
		}
		
		return distance;
	}

}
