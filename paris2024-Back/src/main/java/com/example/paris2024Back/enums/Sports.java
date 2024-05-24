package com.example.paris2024Back.enums;

public enum Sports {
    SOCCER("Soccer"),
    BASKETBALL("Basketball"),
    POWERLIFTING("Powerlifting"),
    WEIGHTLIFTING("Weightlifting"),
    CYCLING("Cycling"),
    RUNNING("Running"),
    SWIMMING("Swimming");

    private final String name;

    Sports(String name) {
    this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Sports fromString(String name) {
        for (Sports sport : Sports.values()) {
            if (sport.name().equalsIgnoreCase(name)) {
                return sport;
            }
        }
        throw new IllegalArgumentException("No enum constant for sport name: " + name);
    }
}
