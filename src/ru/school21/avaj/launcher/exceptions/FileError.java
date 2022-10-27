package ru.school21.avaj.launcher.exceptions;

public class FileError extends  RuntimeException{

    public FileError() {
        super("Ошибка в файле");
    }
}
