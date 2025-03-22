package com.parkinglot.vehicle;

public class EvCar extends Vehicle {
    public EvCar(String licensePlate) {
        super(licensePlate, "EV-Charging");
    }

    @Override
    public String getVehicleSize() {
        return "ev-charging";
    }

    @Override
    public double calculateAmount(long timeSpent) {
        return (double) timeSpent * 3.0;
    }
}
