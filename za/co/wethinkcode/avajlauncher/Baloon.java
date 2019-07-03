package za.co.wethinkcode.avajlauncher;

public class Baloon extends Aircraft implements Flyable 
{
	/*
    * Attributes
    */
	private WeatherTower	weatherTower;
	/*
    * Constructor
    */
	public			Baloon(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		return ;
	}

	/*
    * Methods
    */
	public void		updateConditions()
	{
		String conditions = weatherTower.getWeather(coordinates);
		if (conditions.equals("RAIN"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
		}
		else if (conditions.equals("FOG"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
		}
		else if (conditions.equals("SUN"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
		}
		else if (conditions.equals("SNOW"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
		}
		return ;
	}

	public void		registerTower(WeatherTower weatherTower)
	{
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.logMessage.add("Tower says: Baloon#"+name+"("+id+") registered to weather tower.");
		return ;
	}
}
