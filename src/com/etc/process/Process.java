package com.etc.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.etc.domain.VehicleType;
import com.etc.exception.RideException;
import com.etc.repository.Cards;
import com.etc.repository.Config;
import com.etc.repository.Rides;

/**
 * Drives the entire process.
 * 
 * @author kaushik
 *
 */
public class Process {

	private Cards cards;

	private Rides rides;

	private BufferedReader br;

	public static void main(String[] args) {
		Process process = new Process();
		process.br = new BufferedReader(new InputStreamReader(System.in));
		process.init();
		process.begin();
		process.exit();
	}

	/**
	 * Initialize system and register cards.
	 */
	private void init() {
		loadConfig();
		cards = new Cards();

		System.out.println("Regiter Cards: (enter 'q' to stop adding cards)");
		try {
			while (true) {
				System.out.print(
						"Enter card details in the format (vehicle number, vehicle type, user name, initial amount) : ");
				String input = br.readLine();

				if ("q".equals(input)) {
					break;
				}

				try {
					String[] values = input.split(",");
					cards.registerCard(values[0].trim(), VehicleType.valueOf(values[1].trim().toUpperCase()),
							values[2].trim(), Float.parseFloat(values[3].trim()));
				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					System.err.println("Invalid input!");
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Begin the automated process by registering entry and exit of cards.
	 */
	private void begin() {
		rides = new Rides(cards);

		System.out.println("Entry/Exit: (enter 'q' to stop adding)");
		try {
			while (true) {
				System.out.print(
						"Enter vehicle number, entry/exit gate (example: EN1 for entry at 1, EX1 for exit at 1  ) : ");
				String input = br.readLine();

				if ("q".equals(input)) {
					break;
				}

				try {
					regiterEntryExit(input);
				} catch (RideException e) {
					System.err.println(e.getMessage());
					continue;
				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					System.err.println("Invalid input!");
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Registers entry and exits
	 * 
	 * @param input
	 *            the input received from user/automated system
	 * @throws RideException
	 */
	private void regiterEntryExit(String input) throws RideException {
		String[] values = input.split(",");
		int gateNumber = Integer.parseInt(values[1].trim().substring(2));
		if (values[1].trim().startsWith("EN")) {
			rides.entry(values[0], Config.gates.get(gateNumber));
		} else {
			rides.exit(values[0], Config.gates.get(gateNumber));
		}
	}

	/**
	 * Loads configuration, will reload config if called during runtime
	 */
	private void loadConfig() {
		Config.loadPropeties();
	}

	/**
	 * Close input stream and exit.
	 */
	private void exit() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
