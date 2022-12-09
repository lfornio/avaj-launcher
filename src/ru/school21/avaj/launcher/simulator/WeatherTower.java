package ru.school21.avaj.launcher.simulator;

import ru.school21.avaj.launcher.simulator.aircrafts.Coordinates;
import ru.school21.avaj.launcher.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        this.conditionsChanged();
    }
}
