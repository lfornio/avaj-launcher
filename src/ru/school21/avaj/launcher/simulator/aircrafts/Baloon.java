package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.aircrafts.Aircraft;
import ru.school21.avaj.launcher.simulator.aircrafts.Flyable;

public class Baloon extends Aircraft implements Flyable {
//    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

    }

//    @Override
//    public void registerTower(WeatherTower weatherTower) {
//
//    }


    @Override
    public String toString() {
        return "Baloon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
