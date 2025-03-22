package com.parkinglot.parkinglot;

import com.parkinglot.vehicle.Vehicle;

public class ParkingSpot {
    private final int spotNumber;
    private final int floorNumber;
    private final SpotType spotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, int floorNumber, SpotType spotType) {
        this.spotNumber = spotNumber;
        this.floorNumber = floorNumber;
        this.spotType = spotType;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        if (!isOccupied && vehicle.getVehicleSize().equalsIgnoreCase(spotType.getValue())) {
            isOccupied = true;
            parkedVehicle = vehicle;
            System.out.println("Vehicle with licensePlate: " + vehicle.getLicensePlate() + " is parked at spot: " + spotNumber + "in floor: " + floorNumber);
            return;
        }
        System.out.println("Spot: " + spotNumber + " is already occupied");
    }

    public void unParkVehicle() {
        if (isOccupied) {
            parkedVehicle = null;
            isOccupied = false;
            return;
        }
        System.out.println("Spot: " + spotNumber + " is already empty");
    }
}
