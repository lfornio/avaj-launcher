package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.exceptions.CoordinatesError;
import ru.school21.avaj.launcher.simulator.exceptions.ErrorTypeAircraft;

public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws ErrorTypeAircraft, CoordinatesError {

        if (longitude < 0 || latitude < 0 || height < 0 || height > 100) {
            throw new CoordinatesError();
        }

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        Aircrafts air = Aircrafts.getFromString(type);
        if (air == null) {
            throw new ErrorTypeAircraft();
        }

        switch (air) {
            case BALLOON:
                return new Balloon(name, coordinates);
            case JETPLANE:
                return new JetPlane(name, coordinates);
            case HELICOPTER:
                return new Helicopter(name, coordinates);
            default:
                throw new ErrorTypeAircraft();
        }
    }
}
