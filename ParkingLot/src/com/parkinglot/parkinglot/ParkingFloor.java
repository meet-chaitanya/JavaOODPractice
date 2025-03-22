package com.parkinglot.parkinglot;

import com.parkinglot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber, int numOfSpots) {
        this.floorNumber = floorNumber;
        parkingSpots = new ArrayList<>(numOfSpots);

        for (int i = 0; i < numOfSpots; i++) {
            SpotType spotType = (i % 3 == 0)  ? SpotType.LARGE : (i % 5 == 0) ? SpotType.EV : SpotType.COMPACT;
            parkingSpots.add(new ParkingSpot(i, floorNumber, spotType));
        }
    }

    public Optional<ParkingSpot> findAvailableSpot(SpotType spotType) {
        return parkingSpots.stream()
                .filter(parkingSpot -> parkingSpot.getSpotType().equals(spotType) && !parkingSpot.isOccupied())
                .findFirst();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        SpotType spotType = SpotType.findByValue(vehicle.getVehicleSize());
        if (spotType == null) {
            System.out.println("Invalid vehicle size passed..");
            return false;
        }
        Optional<ParkingSpot> spotOptional = findAvailableSpot(spotType);
        if (spotOptional.isEmpty()) {
            System.out.println("SpotType: " + spotType.getValue() + " is not free in the floor: " + floorNumber);
            return false;
        }
        ParkingSpot spot = spotOptional.get();
        spot.parkVehicle(vehicle);
        return true;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot: parkingSpots) {
            if (spot.isOccupied() && spot.getParkedVehicle().equals(vehicle)) {
                spot.unParkVehicle();
                return true;
            }
        }
        System.out.println("Vehicle: " + vehicle.getLicensePlate() + " is not present in the ParkingFloor: " + floorNumber);
        return false;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}
