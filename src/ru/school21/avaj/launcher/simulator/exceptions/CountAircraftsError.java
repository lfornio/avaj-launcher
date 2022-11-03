package ru.school21.avaj.launcher.simulator.exceptions;

public class CountAircraftsError extends RuntimeException{

    public CountAircraftsError() {
        super("В файле нет воздушных средств");
    }

}
