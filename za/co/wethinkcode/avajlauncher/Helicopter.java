package za.co.wethinkcode.avajlauncher;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public Helicopter(final String name, final Coordinates coordinates) {
		super(name, coordinates);
	}

	public void	updateConditions() {
		final String conditions = weatherTower.getWeather(coordinates);
		switch (conditions) {
			case "RAIN":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() + 5);
				Simulator.LOG_MESSAGE.add("Helicopter#" + name + "(" + id + ") oh crap its raining.");
				break;
			case "FOG":
				this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
				Simulator.LOG_MESSAGE.add("Helicopter#" + name + "(" + id + ") oh wow we not seeing a thing.");
				break;
			case "SUN":
				this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
				Simulator.LOG_MESSAGE.add("Helicopter#" + name + "(" + id + ") let's enjoy the the sun.");
				break;
			case "SNOW":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
				Simulator.LOG_MESSAGE.add("Helicopter#" + name + "(" + id + ") wow its so cold.");
				break;
		}
		if (coordinates.getHeight() == 0) {
			Simulator.LOG_MESSAGE.add("Helicopter#"+name+"("+id+") Landing ...");
			weatherTower.unregister(this);
			Simulator.LOG_MESSAGE.add("Tower says: Helicopter#"+name+"("+id+") unregistered from weather tower.");
			Simulator.LOG_MESSAGE.add("Helicopter#"+name+"("+id+"): Coordinates -> latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude());
		}
	}

	public void	registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.LOG_MESSAGE.add("Tower says: Helicopter#"+name+"("+id+") registered to weather tower.");
	}
}
