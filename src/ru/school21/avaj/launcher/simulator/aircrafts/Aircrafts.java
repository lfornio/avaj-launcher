package ru.school21.avaj.launcher.simulator.aircrafts;

public enum Aircrafts {
    BALLOON("BALLOON"),
    JETPLANE("JETPLANE"),
    HELICOPTER("HELICOPTER");

    private String description;

    Aircrafts(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Aircrafts getFromString(String str) {
        for(Aircrafts air : Aircrafts.values()) {
            if(air.description.equalsIgnoreCase(str)){
                return air;
            }
        }
        return null;
    }
}
