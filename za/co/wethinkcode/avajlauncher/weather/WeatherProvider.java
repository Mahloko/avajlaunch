package za.co.wethinkcode.avajlauncher.weather;

import za.co.wethinkcode.avajlauncher.Coordinates;

public class WeatherProvider {
    private static final String[] weather  = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final WeatherProvider  weatherProvider = new WeatherProvider();

    private WeatherProvider() { }

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(final Coordinates coordinates) {
        int i = coordinates.getHeight() + coordinates.getLongitude() + coordinates.getLatitude();
        return WeatherProvider.weather[i%4];
    }
}
