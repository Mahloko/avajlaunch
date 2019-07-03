package za.co.wethinkcode.avajlauncher.weather;

import za.co.wethinkcode.avajlauncher.Coordinates;

public class WeatherProvider
{
    /*
    * Attributes
    */
    private static WeatherProvider  weatherProvider = new WeatherProvider();
    private static String[]         weather  = {"RAIN", "FOG", "SUN", "SNOW"};

    /*
    * Constructor
    */
    private WeatherProvider() { return ; }

    /*
    * Methods
    */
    public static WeatherProvider   getProvider()
    {
        return WeatherProvider.weatherProvider;
    }

    public String                   getCurrentWeather(Coordinates coordinates)
    {
        int longi = coordinates.getLongitude();
        int lat = coordinates.getLatitude();
        int h = coordinates.getHeight();
        int i = h + longi + lat;
        return WeatherProvider.weather[i%4];
    }
}
