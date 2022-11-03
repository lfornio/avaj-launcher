package ru.school21.avaj.launcher.simulator.exceptions;

public class ErrorTypeAircraft extends RuntimeException{

    public ErrorTypeAircraft() {
        super("Неизвестный тип воздушного судна");
    }

}
