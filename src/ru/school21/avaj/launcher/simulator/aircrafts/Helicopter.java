package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.weather.Weather;
import ru.school21.avaj.launcher.simulator.WeatherTower;

import static ru.school21.avaj.launcher.simulator.aircrafts.Aircrafts.HELICOPTER;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        long longitude = coordinates.getLongitude();
        long latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();


        switch (Weather.valueOf(weather)) {
            case RAIN:
                longitude += 5;
                break;
            case FOG:
                longitude += 1;
                break;
            case SUN:
                height += 2;
                longitude += 10;
                break;
            case SNOW:
                height -= 12;
                break;
        }

        if (height > 100) {
            height = 100;
        }
        if (longitude >= Integer.MAX_VALUE) {
            longitude = Integer.MAX_VALUE;
        }
        if (latitude >= Integer.MAX_VALUE) {
            latitude = Integer.MAX_VALUE;
        }

        Coordinates newCoordinates = new Coordinates((int) longitude, (int) latitude, height);
        coordinates = newCoordinates;

        logger.write(HELICOPTER.getValue() + "#" + name + "(" + id + "): " + Weather.valueOf(weather).getMessage());

        if (coordinates.getHeight() <= 0) {
            logger.write(HELICOPTER.getValue() + "#" + name + "(" + id + ") landing.");
            logger.write("Tower says: " + HELICOPTER.getValue() + "#" + name + "(" + id + ")" + " unregistered to weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        logger.write("Tower says: " + HELICOPTER.getValue() + "#" + name + "(" + id + ")" + " registered to weather tower.");
    }
}
