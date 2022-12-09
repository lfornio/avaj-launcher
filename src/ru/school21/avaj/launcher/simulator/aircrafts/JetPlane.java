package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.weather.Weather;
import ru.school21.avaj.launcher.simulator.WeatherTower;

import static ru.school21.avaj.launcher.simulator.aircrafts.Aircrafts.JETPLANE;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        long longitude = coordinates.getLongitude();
        long latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (Weather.valueOf(weather)) {
            case RAIN:
                latitude += 5;
                break;
            case FOG:
                latitude += 1;
                break;
            case SUN:
                height += 2;
                latitude += 10;
                break;
            case SNOW:
                height -= 7;
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

        logger.write(JETPLANE.getValue() + "#" + name + "(" + id + "): " + Weather.valueOf(weather).getMessage());

        if (coordinates.getHeight() <= 0) {
            logger.write(JETPLANE.getValue() + "#" + name + "(" + id + ") landing.");
            logger.write("Tower says: " + JETPLANE.getValue() + "#" + name + "(" + id + ")" + " unregistered to weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        logger.write("Tower says: " + JETPLANE.getValue() + "#" + name + "(" + id + ")" + " registered to weather tower.");

    }
}
