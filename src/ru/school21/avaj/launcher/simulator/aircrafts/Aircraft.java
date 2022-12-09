package ru.school21.avaj.launcher.simulator.aircrafts;

import ru.school21.avaj.launcher.simulator.Logger;

import static ru.school21.avaj.launcher.simulator.Logger.getLogger;

public abstract class Aircraft {
    protected Long id;
    protected String name;
    protected Coordinates coordinates;
    private static Long idCounter;
    protected Logger logger = getLogger();


    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private Long nextId() {
        if (idCounter == null) {
            idCounter = 0L;
        }
        return ++idCounter;
    }
}
