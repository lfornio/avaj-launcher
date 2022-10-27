package ru.school21.avaj.launcher.exceptions;

public class ArgumentError extends  RuntimeException{

    public ArgumentError() {
        super("Неверное количество аргументов");
    }
}
