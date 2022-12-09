package ru.school21.avaj.launcher.simulator.weather;

import ru.school21.avaj.launcher.simulator.aircrafts.Coordinates;

import static ru.school21.avaj.launcher.simulator.weather.Weather.*;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static String[] weather;

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        long sum = ((long) coordinates.getLongitude() + (long) coordinates.getLatitude() + (long) coordinates.getHeight());
        int res = (int) (sum % 4);
        switch (res) {
            case 0:
                return RAIN.getValue();
            case 1:
                return FOG.getValue();
            case 2:
                return SUN.getValue();
            case 3:
                return SNOW.getValue();
        }
        return "";
    }
}
