package ru.school21.avaj.launcher.exceptions;

public class CountSimulationsError extends RuntimeException{

    public CountSimulationsError() {
        super("Неверное количество симуляций");
    }

}
