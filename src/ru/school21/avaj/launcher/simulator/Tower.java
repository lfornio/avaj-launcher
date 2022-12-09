package ru.school21.avaj.launcher.simulator;

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

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            int size = observers.size();
            observers.get(i).updateConditions();
            if (observers.size() < size) {
                i -= size - observers.size();
            }
        }
    }
}
