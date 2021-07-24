package za.co.wethinkcode.avajlauncher;

public class Balloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	public Balloon(final String name, final Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		final String conditions = weatherTower.getWeather(coordinates);
		switch (conditions) {
			case "RAIN":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
				Simulator.LOG_MESSAGE.add("Balloon#" + name + "(" + id + ") this rain is everywhere we gaining down.");
				break;
			case "FOG":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
				Simulator.LOG_MESSAGE.add("Balloon#" + name + "(" + id + ") can't see a thing out here.");
				break;
			case "SUN":
				this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
				Simulator.LOG_MESSAGE.add("Balloon#" + name + "(" + id + ") let's enjoy the weather.");
				break;
			case "SNOW":
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
				Simulator.LOG_MESSAGE.add("Balloon#" + name + "(" + id + ") its freezing balls out here.");
				break;
		}
		if (coordinates.getHeight() == 0) {
			Simulator.LOG_MESSAGE.add("Balloon#"+name+"("+id+") Landing ...");
			weatherTower.unregister(this);
			Simulator.LOG_MESSAGE.add("Tower says: Balloon#"+name+"("+id+") unregistered from weather tower.");
			Simulator.LOG_MESSAGE.add("Balloon#"+name+"("+id+"): Coordinates -> latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude());
		}
	}

	public void registerTower(final WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.LOG_MESSAGE.add("Tower says: Balloon#"+name+"("+id+") registered to weather tower.");
	}
}
