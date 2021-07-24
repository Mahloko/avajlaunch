package za.co.wethinkcode.avajlauncher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	public static int SIMULATIONS = 0;
	public static List<Flyable> VEHICLES = new ArrayList<>();
	public static List<String> LOG_MESSAGE = new ArrayList<>();
	public static List<String> FILE_CONTENTS = new ArrayList<>();
	
	public static void main(String[] args) {
		final WeatherTower tower = new WeatherTower();
		try (BufferedReader reader  = new BufferedReader(new FileReader(args[0]))) {
			scenarioReader(reader);
			parseScenario();
			for (Flyable fly: VEHICLES)
			fly.registerTower(tower);
			while (SIMULATIONS-- > 0)
				tower.changeWeather();
			try (BufferedWriter writer =  new BufferedWriter(new FileWriter("simulation.txt"))) {
				scenarioWriter(writer);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} catch (SimulatorException e) {
			System.out.println(e.getMessage());
			if (e.getCause() != null)
				System.out.println("Original exception: " + e.getCause().getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void	scenarioReader(BufferedReader reader) throws IOException {
		String line;
		while ((line = reader.readLine()) != null)
			FILE_CONTENTS.add(line);
	}

	private static void	scenarioWriter(BufferedWriter writer) throws IOException {
		for (String message: LOG_MESSAGE) {
			writer.write(message);
			writer.newLine();
		}
	}

	private static void parseScenario() throws SimulatorException {
		String[] parts;
		int j;
		try {
			SIMULATIONS = Integer.parseInt(FILE_CONTENTS.get(0));
			if (SIMULATIONS < 1)
				throw new SimulatorException("Number of circles can't be less than 1", FILE_CONTENTS.get(0));
		} catch(NumberFormatException e) {
			throw new SimulatorException("Expected a number", FILE_CONTENTS.get(0), e);
		}
		for(j = 1; j < FILE_CONTENTS.size(); j++) {
			parts = FILE_CONTENTS.get(j).split(" ");
			if (parts.length != 5)
				throw new SimulatorException("Invalid line format", FILE_CONTENTS.get(j));
			if (Integer.parseInt(parts[2]) < 0 || Integer.parseInt(parts[3]) < 0 || Integer.parseInt(parts[4]) < 0)
				throw new SimulatorException("longitude, latitude and height are supposed to be positive numbers", FILE_CONTENTS.get(j));
			if (parts[0].equals("Balloon") || parts[0].equals("JetPlane") || parts[0].equals("Helicopter")) {
				try {
					VEHICLES.add(AircraftFactory.newAircraft(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
				} catch(NumberFormatException e) {
					throw new SimulatorException("Expected a number", FILE_CONTENTS.get(j), e);
				}
			}
			else throw new SimulatorException(parts[0]+" is not recognized as an aircraft on line", "'"+j+"'");
		}
	}
}
