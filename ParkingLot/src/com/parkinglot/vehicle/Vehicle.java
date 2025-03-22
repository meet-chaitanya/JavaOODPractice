package com.parkinglot.vehicle;

public abstract class Vehicle {
    private final String licensePlate;
    private final String vehicleType;

    public Vehicle(String licensePlate, String vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public abstract String getVehicleSize();
    public abstract double calculateAmount(long timeSpent);
}
