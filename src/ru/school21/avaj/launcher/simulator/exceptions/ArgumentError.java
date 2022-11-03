package ru.school21.avaj.launcher.simulator.exceptions;

public class ArgumentError extends  RuntimeException{

    public ArgumentError() {
        super("Неверное количество аргументов");
    }
}
