package ru.school21.avaj.launcher.simulator.weather;

import ru.school21.avaj.launcher.simulator.aircrafts.Flyable;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
        }
    }
    protected abstract void conditionsChanged();
}
