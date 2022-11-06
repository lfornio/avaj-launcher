package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.Simulator;
import ru.school21.avaj.launcher.simulator.exceptions.CoordinatesError;
import ru.school21.avaj.launcher.simulator.exceptions.ErrorTypeAircraft;
import ru.school21.avaj.launcher.simulator.exceptions.FileError;

public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws ErrorTypeAircraft, CoordinatesError {

        if(longitude < 0 || latitude < 0 || height < 0 || height > 100) {
            throw new CoordinatesError();
        }

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type) {
            case Simulator.BALLOON:
                return new Baloon(name, coordinates);
            case Simulator.JETPLANE:
                return new JetPlane(name, coordinates);
            case Simulator.HELICOPTER:
                return new Helicopter(name, coordinates);
            default:
                throw new ErrorTypeAircraft();
        }
    }
}
