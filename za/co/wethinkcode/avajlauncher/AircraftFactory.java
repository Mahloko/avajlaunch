package za.co.wethinkcode.avajlauncher;

public abstract class AircraftFactory
{
	public static Flyable newAircraft(final String type, final String name, final int longitude, final int latitude, final int height) {
		final Coordinates coordinates = new Coordinates(Math.max(longitude, 0), Math.max(latitude, 0), height <= 100 ? (Math.max(height, 0)) : 100);
		switch (type) {
			case "Balloon":
				return new Balloon(name, coordinates);
			case "JetPlane":
				return new JetPlane(name, coordinates);
			case "Helicopter":
				return new Helicopter(name, coordinates);
		}
		return null;
	}
}
