package ru.school21.avaj.launcher.exceptions;

public class ErrorTypeAircraft extends RuntimeException{

    public ErrorTypeAircraft() {
        super("Неизвестный тип воздушного судна");
    }

}
