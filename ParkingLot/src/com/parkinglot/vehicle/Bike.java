package com.parkinglot.vehicle;

public class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate, "BIKE");
    }

    @Override
    public String getVehicleSize() {
        return "compact";
    }

    @Override
    public double calculateAmount(long timeSpent) {
        return (double) timeSpent / 60;
    }
}
