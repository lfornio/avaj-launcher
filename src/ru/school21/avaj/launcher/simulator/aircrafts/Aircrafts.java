package ru.school21.avaj.launcher.simulator.aircrafts;

public enum Aircrafts {
    BALLOON("Balloon"),
    JETPLANE("JetPlane"),
    HELICOPTER("Helicopter");

    private String value;

    Aircrafts(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Aircrafts getFromString(String str) {
        for (Aircrafts air : Aircrafts.values()) {
            if (air.value.equalsIgnoreCase(str)) {
                return air;
            }
        }
        return null;
    }
}
