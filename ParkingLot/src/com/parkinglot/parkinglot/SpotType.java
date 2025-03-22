package com.parkinglot.parkinglot;

public enum SpotType {
    COMPACT("compact"), LARGE("large"), EXTRALARGE("extra-large"), EV("ev-charging");

    private final String value;

    SpotType(String value) {
        this.value = value;
    }

    public static SpotType findByValue(String value) {
        for (SpotType spotType: values()) {
            if (spotType.getValue().equalsIgnoreCase(value)) {
                return spotType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
