/**
 * 
 */
package com.etc.rate;

import com.etc.domain.Gate;
import com.etc.domain.VehicleType;
import com.etc.repository.Config;

/**
 * Compute Fixed Rate
 * 
 * @author kaushik
 */
public class FixedRate implements Rate {

	/* (non-Javadoc)
	 * @see com.etc.rate.Rate#getCharges(com.etc.domain.VehicleType, com.etc.domain.Gate, com.etc.domain.Gate)
	 */
	@Override
	public float getCharges(VehicleType vehicleType, Gate entryGate, Gate exitGate) {
		int startGateId = entryGate.getId();
		int endGateId = exitGate.getId();

		int charges = 0;
		
		int currentGateId = startGateId;
		while (currentGateId != endGateId) {
			if (VehicleType.LMV == vehicleType) {
				charges += Config.gateConfig.get(currentGateId).lmv;
			} else if (VehicleType.LCV == vehicleType) {
				charges += Config.gateConfig.get(currentGateId).lcv;
			}
			
			currentGateId = Config.gateConfig.get(currentGateId).exId;
		}
		
		return charges;
	}
}