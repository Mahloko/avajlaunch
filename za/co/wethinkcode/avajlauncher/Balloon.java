package za.co.wethinkcode.avajlauncher;

public class Balloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	public Balloon(final String name, final Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		final String conditions = weatherTower.getWeather(this.coordinates);
		switch (conditions) {
			case "RAIN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
				Simulator.LOG_MESSAGE.add("Balloon#"+this.name+"("+this.id +") this rain is everywhere we gaining down.");
				break;
			case "FOG":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
				Simulator.LOG_MESSAGE.add("Balloon#"+this.name+"("+this.id+") can't see a thing out here.");
				break;
			case "SUN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
				Simulator.LOG_MESSAGE.add("Balloon#"+this.name+"("+this.id+") let's enjoy the weather.");
				break;
			case "SNOW":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
				Simulator.LOG_MESSAGE.add("Balloon#"+this.name+"("+this.id+") its freezing balls out here.");
				break;
		}

		if (this.coordinates.getHeight() == 0) {
			Simulator.LOG_MESSAGE.add("Balloon#"+this.name+"("+this.id+") Landing ...");
			this.weatherTower.unregister(this);
			Simulator.LOG_MESSAGE.add("Tower says: Balloon#"+this.name+"("+this.id+") unregistered from weather tower.");
			Simulator.LOG_MESSAGE.add("Balloon#"+this.name+"("+this.id+"): Coordinates -> latitude: "+this.coordinates.getLatitude()+" longitude: "+this.coordinates.getLongitude());
		}
	}

	public void registerTower(final WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.LOG_MESSAGE.add("Tower says: Balloon#"+this.name+"("+this.id+") registered to weather tower.");
	}
}
