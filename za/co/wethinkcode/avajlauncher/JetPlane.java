package za.co.wethinkcode.avajlauncher;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public JetPlane(final String name, final Coordinates coordinates) {
		super(name, coordinates);
	}

	public void	updateConditions() {
		final String conditions = weatherTower.getWeather(coordinates);
		switch (conditions) {
			case "RAIN":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
				Simulator.LOG_MESSAGE.add("JetPlane#" + name + "(" + id + ") its raining be careful of the thunder.");
				break;
			case "FOG":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
				Simulator.LOG_MESSAGE.add("JetPlane#" + name + "(" + id + ") hope we don't crash, I can't see a thing.");
				break;
			case "SUN":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
				Simulator.LOG_MESSAGE.add("JetPlane#" + name + "(" + id + ") let's enjoy the weather its sunny out here.");
				break;
			case "SNOW":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
				Simulator.LOG_MESSAGE.add("JetPlane#" + name + "(" + id + ") lets keep the engine warm its freezing out here.");
				break;
		}
		if (coordinates.getHeight() == 0) {
			Simulator.LOG_MESSAGE.add("JetPlane#"+name+"("+id+") Landing ...");
			weatherTower.unregister(this);
			Simulator.LOG_MESSAGE.add("Tower says: JetPlane#"+name+"("+id+") unregistered from weather tower.");
			Simulator.LOG_MESSAGE.add("JetPlane#"+name+"("+id+"): Coordinates -> latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude());
		}
	 }

	public void	registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.LOG_MESSAGE.add("Tower says: JetPlane#"+name+"("+id+") registered to weather tower.");
	}
}
