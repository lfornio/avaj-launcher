package ru.school21.avaj.launcher.simulator;

import ru.school21.avaj.launcher.exceptions.*;
import ru.school21.avaj.launcher.simulator.aircrafts.AircraftFactory;
import ru.school21.avaj.launcher.simulator.aircrafts.Flyable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static final int COUNT_INFO_FROM_FILE = 5;
    private static List<Flyable> aircrafts = new ArrayList<>();

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new ArgumentError();
            }
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            int countSimulations = getCountSimulationsFromFile(reader.readLine());

            String line;
            while ((line = reader.readLine()) != null) {
                Flyable flyable = getFlyablesFromFile(line);
                aircrafts.add(flyable);
            }
            if (aircrafts.isEmpty()) {
                throw new CountAircraftsError();
            }

            for (Flyable flyable : aircrafts) {
                System.out.println(flyable.toString());
            }

        } catch (ArgumentError | ErrorTypeAircraft | FileError | CountAircraftsError | CountSimulationsError ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
            System.exit(1);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка в файле");
            System.exit(1);
        }
    }

    private static int getCountSimulationsFromFile(String line) throws FileError, CountSimulationsError {
        int countSimulations;
        try {
            countSimulations = Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            throw new FileError();
        }
        if (countSimulations <= 0 || countSimulations > Integer.MAX_VALUE) {
            throw new CountSimulationsError();
        }
        return countSimulations;
    }

    private static Flyable getFlyablesFromFile(String line) throws FileError, NumberFormatException, ErrorTypeAircraft {
        String[] array = line.split(" ");
        if (array.length != COUNT_INFO_FROM_FILE) {
            throw new FileError();
        }

        Flyable flyable = AircraftFactory.newAircraft(
                line.split(" ")[0],
                line.split(" ")[1],
                Integer.parseInt(line.split(" ")[2]),
                Integer.parseInt(line.split(" ")[3]),
                Integer.parseInt(line.split(" ")[4])
        );
        return flyable;
    }
}
