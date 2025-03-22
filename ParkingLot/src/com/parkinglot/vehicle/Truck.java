package com.parkinglot.vehicle;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, "TRUCK");
    }

    @Override
    public String getVehicleSize() {
        return "extra-large";
    }

    @Override
    public double calculateAmount(long timeSpent) {
        return (double) timeSpent * 2.0;
    }
}
