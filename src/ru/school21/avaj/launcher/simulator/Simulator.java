package ru.school21.avaj.launcher.simulator;

import ru.school21.avaj.launcher.simulator.aircrafts.*;
import ru.school21.avaj.launcher.simulator.exceptions.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static ru.school21.avaj.launcher.simulator.Logger.getLogger;
import static ru.school21.avaj.launcher.simulator.aircrafts.Aircrafts.*;

public class Simulator {
    private static final int COUNT_INFO_FROM_FILE = 5;
    private static List<Flyable> aircrafts = new ArrayList<>();

    private static Logger logger = getLogger();

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new ArgumentError();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int countSimulations = getCountSimulationsFromFile(reader.readLine());
            receiveFlyables(reader);
            WeatherTower weatherTower = new WeatherTower();
            for (Flyable flyable : aircrafts) {
                flyable.registerTower(weatherTower);
            }
            for (int i = 0; i < countSimulations; i++) {
                logger.write(i + "---------------------");
                weatherTower.changeWeather();
                aircraftInOnePlate();
            }
        } catch (AppExceptions ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
            System.exit(1);
        } catch (NumberFormatException | NoSuchFieldException | IOException | IllegalAccessException ex) {
            System.out.println("Ошибка в файле");
            System.exit(1);
        } finally {
            logger.close();
        }
    }

    private static void receiveFlyables(BufferedReader reader) throws IOException, DublicateError, ConflictError, NoSuchFieldException, IllegalAccessException {
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

    private static void aircraftsHasDublicate() throws DublicateError, NoSuchFieldException, IllegalAccessException {
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

    private static void aircraftInOnePlate() throws ConflictError, NoSuchFieldException, IllegalAccessException {
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

    private static String getTypeAndNameAircraft(Flyable flyable) throws NoSuchFieldException, IllegalAccessException {
        Field field = flyable.getClass().getSuperclass().getDeclaredField("name");
        field.setAccessible(true);
        String name = (String) field.get(flyable);
        if (flyable instanceof Balloon) {
            return BALLOON.getValue() + name;
        }
        if (flyable instanceof JetPlane) {
            return JETPLANE.getValue() + name;
        }
        if (flyable instanceof Helicopter) {
            return HELICOPTER.getValue() + name;
        }
        return "";
    }

    private static Coordinates getCoordinatesAircraft(Flyable flyable) throws NoSuchFieldException, IllegalAccessException {
        Field field = flyable.getClass().getSuperclass().getDeclaredField("coordinates");
        field.setAccessible(true);
        return (Coordinates) field.get(flyable);
    }
}
