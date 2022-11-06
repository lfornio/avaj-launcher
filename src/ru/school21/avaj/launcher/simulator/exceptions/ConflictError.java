package ru.school21.avaj.launcher.simulator.exceptions;

public class ConflictError extends RuntimeException{
    public ConflictError() {
        super("Летательные средства находятся в одной точке");
    }

}
