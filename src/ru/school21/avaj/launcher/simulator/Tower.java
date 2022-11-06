package ru.school21.avaj.launcher.simulator;

import ru.school21.avaj.launcher.simulator.aircrafts.Baloon;
import ru.school21.avaj.launcher.simulator.aircrafts.Flyable;
import ru.school21.avaj.launcher.simulator.aircrafts.Helicopter;
import ru.school21.avaj.launcher.simulator.aircrafts.JetPlane;

import java.util.ArrayList;
import java.util.List;

import static ru.school21.avaj.launcher.simulator.Simulator.*;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
        System.out.print("Tower says: ");
        if (flyable instanceof Baloon) {
            System.out.print(BALLOON + "#" + ((Baloon) flyable).getName() + "(" + ((Baloon) flyable).getId() + ")");
        }
        if (flyable instanceof JetPlane) {
            System.out.print(JETPLANE + "#" + ((JetPlane) flyable).getName() + "(" + ((JetPlane) flyable).getId() + ")");
        }
        if (flyable instanceof Helicopter) {
            System.out.print(HELICOPTER + "#" + ((Helicopter) flyable).getName() + "(" + ((Helicopter) flyable).getId() + ")");
        }
        System.out.println(" registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
        }
        System.out.print("Tower says: ");
        if (flyable instanceof Baloon) {
            System.out.print(BALLOON + "#" + ((Baloon) flyable).getName() + "(" + ((Baloon) flyable).getId() + ")");
        }
        if (flyable instanceof JetPlane) {
            System.out.print(JETPLANE + "#" + ((JetPlane) flyable).getName() + "(" + ((JetPlane) flyable).getId() + ")");
        }
        if (flyable instanceof Helicopter) {
            System.out.print(HELICOPTER + "#" + ((Helicopter) flyable).getName() + "(" + ((Helicopter) flyable).getId() + ")");
        }
        System.out.println(" unregistered from weather tower.");
    }
    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}
