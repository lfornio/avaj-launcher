package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.weather.Weather;
import ru.school21.avaj.launcher.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        System.out.println("Weather = " + weather);
        System.out.println("1 = " + this);
        switch (Weather.valueOf(weather)) {
            case RAIN:
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                break;
            case FOG:
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                break;
            case SUN:
                coordinates.setHeight(coordinates.getHeight() + 2);
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                break;
            case SNOW:
                coordinates.setHeight(coordinates.getHeight() - 7);
                break;
            default:
        }
        if (coordinates.getHeight() > 100) {
            coordinates.setHeight(100);
        }

        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
        }

        System.out.println("2 = " + this);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    @Override
    public String toString() {
        return "JetPlane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", weatherTower=" + weatherTower +
                '}';
    }
}
