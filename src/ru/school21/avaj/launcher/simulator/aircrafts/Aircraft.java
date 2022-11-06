package ru.school21.avaj.launcher.simulator.aircrafts;

public abstract class Aircraft {
    protected Long id;
    protected String name;
    protected Coordinates coordinates;
    private static Long idCounter;

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

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
