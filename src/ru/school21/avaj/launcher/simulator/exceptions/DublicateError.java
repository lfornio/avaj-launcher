package ru.school21.avaj.launcher.simulator.exceptions;

public class DublicateError extends RuntimeException {
    public DublicateError() {
        super("В файле дубликат");
    }

}
