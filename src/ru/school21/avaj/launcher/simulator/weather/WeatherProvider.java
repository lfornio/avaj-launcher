package ru.school21.avaj.launcher.simulator.weather;

import ru.school21.avaj.launcher.simulator.aircrafts.Coordinates;

public class WeatherProvider {
    private WeatherProvider weatherProvider;
    private String[] weather;

    private WeatherProvider() {

    }

    public WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return "";
    }
}
