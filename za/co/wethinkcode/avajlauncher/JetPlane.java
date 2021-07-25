package za.co.wethinkcode.avajlauncher;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public JetPlane(final String name, final Coordinates coordinates) {
		super(name, coordinates);
	}

	public void	updateConditions() {
		final String conditions = weatherTower.getWeather(this.coordinates);
		switch(conditions) {
			case "RAIN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
				Simulator.LOG_MESSAGE.add("JetPlane#"+this.name+"("+this.id+") its raining be careful of the thunder.");
				break;
			case "FOG":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
				Simulator.LOG_MESSAGE.add("JetPlane#"+this.name+"("+this.id+") hope we don't crash, I can't see a thing.");
				break;
			case "SUN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
				Simulator.LOG_MESSAGE.add("JetPlane#"+this.name+"("+this.id+") let's enjoy the weather its sunny out here.");
				break;
			case "SNOW":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
				Simulator.LOG_MESSAGE.add("JetPlane#"+this.name+"("+this.id+") lets keep the engine warm its freezing out here.");
				break;
		}
		if (this.coordinates.getHeight() == 0) {
			Simulator.LOG_MESSAGE.add("JetPlane#"+this.name+"("+this.id+") Landing ...");
			this.weatherTower.unregister(this);
			Simulator.LOG_MESSAGE.add("Tower says: JetPlane#"+this.name+"("+this.id+") unregistered from weather tower.");
			Simulator.LOG_MESSAGE.add("JetPlane#"+this.name+"("+this.id+"): Coordinates -> latitude: "+this.coordinates.getLatitude()+" longitude: "+this.coordinates.getLongitude());
		}
	 }

	public void	registerTower(final WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.LOG_MESSAGE.add("Tower says: JetPlane#"+this.name+"("+this.id+") registered to weather tower.");
	}
}
