package com.parkinglot.parkinglot;

import com.parkinglot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingFloor> floors;

    public ParkingLot(int noOfFloors, int spotsPerFloor) {
        floors = new ArrayList<>(noOfFloors);
        for (int i = 1; i <= noOfFloors; i++) {
            ParkingFloor parkingFloor = new ParkingFloor(i, spotsPerFloor);
            floors.add(parkingFloor);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(ParkingFloor floor: floors) {
            if (floor.parkVehicle(vehicle)) {
                System.out.println("parked vehicle: " + vehicle.getLicensePlate() + " at floor: " + floor.getFloorNumber());
                return true;
            }
        }
        System.out.println("ParkingLot is full for vehicle size: " + vehicle.getVehicleSize());
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor: floors) {
            if (floor.unParkVehicle(vehicle)) {
                System.out.println("UnParked vehicle: " + vehicle.getLicensePlate() + " at floor: " + floor.getFloorNumber());
                return true;
            }
        }
        System.out.println("Vehicle: " + vehicle.getLicensePlate() + " is not present in the ParkingLot.");
        return false;
    }
}
