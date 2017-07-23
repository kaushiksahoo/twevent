package com.etc.exception;

/**
 * Exception related to a ride
 * 
 * @author kaushik
 */
public class RideException extends Exception {

	private static final long serialVersionUID = 5764326072779122442L;
	
	public RideException(String message) {
		super(message);
	}
}