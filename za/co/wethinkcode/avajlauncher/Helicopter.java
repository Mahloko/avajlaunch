package za.co.wethinkcode.avajlauncher;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public Helicopter(final String name, final Coordinates coordinates) {
		super(name, coordinates);
	}

	public void	updateConditions() {
		final String conditions = this.weatherTower.getWeather(this.coordinates);
		switch (conditions) {
			case "RAIN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() + 5);
				Simulator.LOG_MESSAGE.add("Helicopter#"+this.name+"("+this.id+") oh crap its raining.");
				break;
			case "FOG":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
				Simulator.LOG_MESSAGE.add("Helicopter#"+this.name+"("+this.id+") oh wow we not seeing a thing.");
				break;
			case "SUN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
				Simulator.LOG_MESSAGE.add("Helicopter#"+this.name+"("+this.id+") let's enjoy the the sun.");
				break;
			case "SNOW":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
				Simulator.LOG_MESSAGE.add("Helicopter#"+this.name+"("+this.id+") wow its so cold.");
				break;
		}
		if (this.coordinates.getHeight() == 0) {
			Simulator.LOG_MESSAGE.add("Helicopter#"+this.name+"("+this.id+") Landing ...");
			this.weatherTower.unregister(this);
			Simulator.LOG_MESSAGE.add("Tower says: Helicopter#"+this.name+"("+this.id+") unregistered from weather tower.");
			Simulator.LOG_MESSAGE.add("Helicopter#"+this.name+"("+this.id+"): Coordinates -> latitude: "+this.coordinates.getLatitude()+" longitude: "+this.coordinates.getLongitude());
		}
	}

	public void	registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.LOG_MESSAGE.add("Tower says: Helicopter#"+this.name+"("+this.id+") registered to weather tower.");
	}
}
