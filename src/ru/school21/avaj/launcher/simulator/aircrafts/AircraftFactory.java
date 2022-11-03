package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.exceptions.ErrorTypeAircraft;
import ru.school21.avaj.launcher.simulator.exceptions.FileError;

public abstract class AircraftFactory {
    private static final String BALLOON = "Balloon";
    private static final String JETPLANE = "JetPlane";
    private static final String HELICOPTER = "Helicopter";

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws ErrorTypeAircraft, FileError {

        if( longitude < 0 || latitude < 0 || height < 0) {
            throw new FileError();
        }

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type) {
            case BALLOON:
                return new Baloon(name, coordinates);
            case JETPLANE:
                return new JetPlane(name, coordinates);
            case HELICOPTER:
                return new Helicopter(name, coordinates);
            default:
                throw new ErrorTypeAircraft();
        }
    }
}
