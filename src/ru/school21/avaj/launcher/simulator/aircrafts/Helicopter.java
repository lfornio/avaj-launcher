package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.aircrafts.Aircraft;
import ru.school21.avaj.launcher.simulator.aircrafts.Flyable;

public class Helicopter extends Aircraft implements Flyable {
//    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
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
        return "Helicopter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
