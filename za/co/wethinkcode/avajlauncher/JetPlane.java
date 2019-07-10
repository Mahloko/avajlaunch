package za.co.wethinkcode.avajlauncher;

public class JetPlane extends Aircraft implements Flyable
{
	/*
    * Attributes
    */
	private WeatherTower	weatherTower;
	
	/*
    * Constructor
    */
	public		JetPlane(String name, Coordinates coordinates)
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
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
			Simulator.logMessage.add("JetPlane#"+name+"("+id+") its raining becareful of the thunder.");
		}
		else if (conditions.equals("FOG"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
			Simulator.logMessage.add("JetPlane#"+name+"("+id+") hope we don't crash, I can't see a thing.");
		}
		else if (conditions.equals("SUN"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
			Simulator.logMessage.add("JetPlane#"+name+"("+id+") let's enjoy the weather its sunny out here.");
		}
		else if (conditions.equals("SNOW"))
		{
			this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
			Simulator.logMessage.add("JetPlane#"+name+"("+id+") lets keep the engine warm its freezing out here.");
		}
		if (coordinates.getHeight() == 0)
		{
			weatherTower.unregister(this);
			Simulator.logMessage.add("JetPlane#"+name+"("+id+") Landing ...");
		}
		return ;
	 }

	public void	registerTower(WeatherTower weatherTower)
	{
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.logMessage.add("Tower says: JetPlane#"+name+"("+id+") registered to weather tower.");
		return ;
	}
}
