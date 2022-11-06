package ru.school21.avaj.launcher.simulator;

import ru.school21.avaj.launcher.simulator.aircrafts.*;
import ru.school21.avaj.launcher.simulator.exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Simulator {
    public static final String BALLOON = "Balloon";
    public static final String JETPLANE = "JetPlane";
    public static final String HELICOPTER = "Helicopter";
    private static final int COUNT_INFO_FROM_FILE = 5;
    private static List<Flyable> aircrafts = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new ArgumentError();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter((new FileWriter("output.txt", true)))) {
            int countSimulations = getCountSimulationsFromFile(reader.readLine());
            receiveFlyables(reader);
            WeatherTower weatherTower = new WeatherTower();
            for (Flyable flyable : aircrafts) {
                flyable.registerTower(weatherTower);
            }

            for (int i = 0; i < countSimulations; i++) {
                weatherTower.changeWeather();
            }

        } catch (ArgumentError | ErrorTypeAircraft | FileError | CountAircraftsError | CountSimulationsError | DublicateError | ConflictError | CoordinatesError ex) {
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

    private static void receiveFlyables(BufferedReader reader) throws IOException, DublicateError, ConflictError {
        String line;
        while ((line = reader.readLine()) != null) {
            Flyable flyable = getFlyableFromFile(line);
            aircrafts.add(flyable);
        }
        if (aircrafts.isEmpty()) {
            throw new CountAircraftsError();
        }
        aircraftsHasDublicate();
        aircraftInOnePlate();

    }

    private static void aircraftsHasDublicate() throws DublicateError {
        for (int i = 0; i < aircrafts.size() - 1; i++) {
            Flyable flyable1 = aircrafts.get(i);
            Flyable flyable2 = aircrafts.get(i + 1);

            String s1 = getTypeAndNameAircraft(flyable1);
            String s2 = getTypeAndNameAircraft(flyable2);

            if (s1.equals(s2)) {
                throw new DublicateError();
            }
        }
    }

    private static void aircraftInOnePlate() throws ConflictError {
        for (int i = 0; i < aircrafts.size() - 1; i++) {
            Flyable flyable1 = aircrafts.get(i);
            Flyable flyable2 = aircrafts.get(i + 1);

            Coordinates coordinates1 = getCoordinatesAircraft(flyable1);
            Coordinates coordinates2 = getCoordinatesAircraft(flyable2);

            if (coordinates1 != null && coordinates1.equals(coordinates2)) {
                throw new ConflictError();
            }
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

    private static Flyable getFlyableFromFile(String line) throws FileError, NumberFormatException, ErrorTypeAircraft, CoordinatesError {
        String[] array = line.split(" ");
        if (array.length != COUNT_INFO_FROM_FILE) {
            throw new FileError();
        }

        Flyable flyable = AircraftFactory.newAircraft(
                array[0],
                array[1],
                Integer.parseInt(array[2]),
                Integer.parseInt(array[3]),
                Integer.parseInt(array[4])
        );

        return flyable;
    }

    private static String getTypeAndNameAircraft(Flyable flyable) {
        if (flyable instanceof Baloon) {
            return BALLOON + ((Baloon) flyable).getName();
        }
        if (flyable instanceof JetPlane) {
            return JETPLANE + ((JetPlane) flyable).getName();
        }
        if (flyable instanceof Helicopter) {
            return HELICOPTER + ((Helicopter) flyable).getName();
        }
        return "";
    }

    private static Coordinates getCoordinatesAircraft(Flyable flyable) {
        if (flyable instanceof Baloon) {
            return ((Baloon) flyable).getCoordinates();
        }
        if (flyable instanceof JetPlane) {
            return ((JetPlane) flyable).getCoordinates();
        }
        if (flyable instanceof Helicopter) {
            return ((Helicopter) flyable).getCoordinates();
        }
        return null;
    }
}
