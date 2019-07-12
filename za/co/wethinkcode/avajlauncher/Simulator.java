package za.co.wethinkcode.avajlauncher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator
{
	public static int i = 0;
	public static List<Flyable>  vehicles = new ArrayList<Flyable>();
	public static List<String>  logMessage = new ArrayList<String>();
	public static List<String>  fileContents = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		WeatherTower tower = new WeatherTower();
		try (BufferedReader reader  = new BufferedReader(new FileReader(args[0])))
		{
			scenarioReader(reader, args[0]);
			parseScenario();
			for (Flyable fly: vehicles)
			fly.registerTower(tower);
			while (i-- > 0)
				tower.changeWeather();
			try (BufferedWriter writter =  new BufferedWriter(new FileWriter("simulation.txt")))
			{
				scenarioWriter(writter);
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		catch (SimulatorException e)
		{
			System.out.println(e.getMessage());
			if (e.getCause() != null)
				System.out.println("Original exception: " + e.getCause().getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private static void	scenarioReader(BufferedReader reader, String file) throws IOException
	{
		String line = null;
		while ((line = reader.readLine()) != null)
			fileContents.add(line);
	}

	private static void	scenarioWriter(BufferedWriter writter) throws IOException
	{
		for (String message: logMessage)
		{
			writter.write(message);
			writter.newLine();
		}
	}

	private static void parseScenario() throws SimulatorException
	{
		String[] parts = null;
		int j;
		try
		{
			i = Integer.valueOf(fileContents.get(0));
			if (i < 1)
				throw new SimulatorException("Number of circles can't be less than 1", fileContents.get(0));
		}
		catch(NumberFormatException e)
		{
			throw new SimulatorException("Expected a number", fileContents.get(0), e);
		}
		for(j = 1; j < fileContents.size(); j++)
		{
			parts = fileContents.get(j).split(" ");
			if (parts.length != 5)
				throw new SimulatorException("Invalid line format", fileContents.get(j));
			if (Integer.valueOf(parts[2]) < 0 || Integer.valueOf(parts[3]) < 0 || Integer.valueOf(parts[4]) < 0)
				throw new SimulatorException("longitude, latitude and height are supposed to be positive numbers", fileContents.get(j));
			if (parts[0].equals("Baloon") || parts[0].equals("JetPlane") || parts[0].equals("Helicopter"))
			{
				try
				{

					vehicles.add(AircraftFactory.newAircraft(parts[0],
															parts[1],
															Integer.valueOf(parts[2]),
															Integer.valueOf(parts[3]),
															Integer.valueOf(parts[4])));
				}
				catch(NumberFormatException e)
				{
					throw new SimulatorException("Expected a number", fileContents.get(j), e);
				}
			}
			else
				throw new SimulatorException(parts[0]+" is not recognized as an aircraft on line", "'"+j+"'");
		}
	}
}
