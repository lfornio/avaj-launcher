package ru.school21.avaj.launcher.simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger logger;
    private static PrintWriter writer;
    private static final String FILE_NAME = "simulation.txt";

    private Logger() {

    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
            try {
                writer = new PrintWriter(new FileWriter(FILE_NAME));
            } catch (IOException e) {
                System.out.println("Файл " + FILE_NAME + " не был создан");
                System.exit(1);
            }
        }
        return logger;
    }

    public void write(String message) {
        writer.println(message);
    }

    public void close() {
        writer.close();
    }

}
