package ru.school21.avaj.launcher.simulator.exceptions;

public class CountSimulationsError extends RuntimeException{

    public CountSimulationsError() {
        super("Неверное количество симуляций");
    }

}
