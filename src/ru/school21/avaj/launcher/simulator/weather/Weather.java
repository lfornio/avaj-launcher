package ru.school21.avaj.launcher.simulator.weather;

public enum Weather {
    RAIN("RAIN"),  //дождь
    FOG("FOG"),  //туман
    SUN("SUN"),
    SNOW("SNOW");

    private String value;

    Weather(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
