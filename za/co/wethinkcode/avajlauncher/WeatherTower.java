package za.co.wethinkcode.avajlauncher;

import za.co.wethinkcode.avajlauncher.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        WeatherProvider wProvider = WeatherProvider.getProvider();
        return wProvider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionsChanged();
    }
}
