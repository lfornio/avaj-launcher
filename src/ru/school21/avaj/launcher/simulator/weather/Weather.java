package ru.school21.avaj.launcher.simulator.weather;

public enum Weather {
    RAIN("RAIN", "I'm sad it's raining :("),
    FOG("FOG", "Where I'm? I don't see anything :0"),
    SUN("SUN", "How hot :)"),
    SNOW("SNOW", "SNOW! SNOW! SNOW! SNOW!");

    private String value;
    private String message;

    Weather(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
