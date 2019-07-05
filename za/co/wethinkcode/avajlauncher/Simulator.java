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
	public static List<Flyable>  vehicles = new ArrayList<Flyable>();
	public static List<String>  logMessage = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		int i = 0;
		BufferedReader reader = null;
		WeatherTower tower = new WeatherTower();
		try {
			reader = new BufferedReader(new FileReader(args[0]));
			String line = null;
			line = reader.readLine();
			i = Integer.valueOf(line);
			while ((line = reader.readLine()) != null)
			{
				String[] parts = line.split(" ");
				vehicles.add(AircraftFactory.newAircraft(parts[0],
														parts[1],
														Integer.valueOf(parts[2]),
														Integer.valueOf(parts[3]),
														Integer.valueOf(parts[4])));
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				if (reader != null)
				reader.close();
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		for (Flyable fly: vehicles)
			fly.registerTower(tower);
			while (i-- > 0)
				tower.changeWeather();
		try (BufferedWriter writter =  new BufferedWriter(new FileWriter("simulation.txt")))
		{
			for (String message: logMessage)
			{
				writter.write(message);
				writter.newLine();
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
