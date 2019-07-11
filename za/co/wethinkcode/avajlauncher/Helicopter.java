package za.co.wethinkcode.avajlauncher;

public class Helicopter extends Aircraft implements Flyable
{
	/*
    * Attributes
    */
	private WeatherTower	weatherTower;

	/*
    * Constructor
    */
	public 		Helicopter(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		return ;
	}
	
	/*
    * Methods
    */
	public void	updateConditions()
	{
		String conditions = weatherTower.getWeather(coordinates);
		if (conditions.equals("RAIN"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() + 5);
			Simulator.logMessage.add("Helicopter#"+name+"("+id+") oh crap its raining.");
		}
		else if (conditions.equals("FOG"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
			Simulator.logMessage.add("Helicopter#"+name+"("+id+") oh wow we not seeing a thing.");
		}
		else if (conditions.equals("SUN"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
			Simulator.logMessage.add("Helicopter#"+name+"("+id+") let's enjoy the the sun.");
		}
		else if (conditions.equals("SNOW"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
			Simulator.logMessage.add("Helicopter#"+name+"("+id+") wow its so cold.");
		}
		if (coordinates.getHeight() == 0)
		{
			Simulator.logMessage.add("Helicopter#"+name+"("+id+") Landing ...");
			weatherTower.unregister(this);
			Simulator.logMessage.add("Tower says: Helicopter#"+name+"("+id+") unregistered from weather tower.");
			Simulator.logMessage.add("Helicopter#"+name+"("+id+"): Coordinates -> latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude());
		}
		return ;
	}

	public void	registerTower(WeatherTower weatherTower)
	{
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.logMessage.add("Tower says: Helicopter#"+name+"("+id+") registered to weather tower.");
		return ;
	}
}
