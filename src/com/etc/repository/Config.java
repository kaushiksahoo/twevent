package com.etc.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.etc.domain.Gate;
import com.etc.domain.VehicleType;

public class Config {

	public static Map<Integer, Gate> gates = new HashMap<>();
	
	public static Map<Integer, GateConfig> gateConfig = new HashMap<>();
	
	public static Map<VehicleType, DistanceConfig> distanceConfig = new HashMap<>();
	
	public static void loadPropeties() {
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("resources/config.properties")) {

			Properties prop = new Properties();
			prop.load(input);

			for (Entry<Object, Object> entry : prop.entrySet()) {
				String propKey = (String) entry.getKey();
				String propValue = (String) entry.getValue();
				
				String objKey = propKey.substring(propKey.indexOf('.') + 1);
				
				if (propKey.startsWith("gates")) {
					addGates(objKey, propValue);
				} else if (propKey.startsWith("gateConfig")) {
					addGateConfig(objKey, propValue);
				} else if (propKey.startsWith("distanceConfig")) {
					addDistanceConfig(objKey, propValue);
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static void addGates(String key, String value) {
		gates.put(Integer.parseInt(key.trim()), new Gate(Integer.parseInt(key), value));
	}

	private static void addGateConfig(String key, String value) {
		String[] values = getValues(value);
		int intKey = Integer.parseInt(key);
		gateConfig.put(intKey, new GateConfig(intKey, 
				Integer.parseInt(values[0].trim()),
				Integer.parseInt(values[1].trim()),
				Float.parseFloat(values[2].trim()),
				Float.parseFloat(values[3].trim())));
	}

	private static void addDistanceConfig(String key, String value) {
		String[] values = getValues(value);
		distanceConfig.put(VehicleType.valueOf(key.toUpperCase()), new DistanceConfig(
				Float.parseFloat(values[0].trim()), 
				Float.parseFloat(values[1].trim())));
	}

	private static String[] getValues(String value) {
		return value.split(",");
	}
	
	public static class GateConfig {
		
		public int enId;
		public int exId;
		public int distance;
		public float lmv;
		public float lcv;

		public GateConfig(int enId, int exId, int distance, float lmv, float lcv) {
			super();
			this.enId = enId;
			this.exId = exId;
			this.distance = distance;
			this.lmv = lmv;
			this.lcv = lcv;
		}
	}

	public static class DistanceConfig {
		
		public float baseChanrge;
		public float charge;
		
		public DistanceConfig(float baseChanrge, float charge) {
			super();
			this.baseChanrge = baseChanrge;
			this.charge = charge;
		}
	}
}