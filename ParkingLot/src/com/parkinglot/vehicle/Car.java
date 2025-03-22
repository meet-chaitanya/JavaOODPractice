package com.parkinglot.vehicle;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, "CAR");
    }

    @Override
    public String getVehicleSize() {
        return "large";
    }

    @Override
    public double calculateAmount(long timeSpent) {
        return (double) timeSpent / 60;
    }
}
