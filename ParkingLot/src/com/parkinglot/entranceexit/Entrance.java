package com.parkinglot.entranceexit;

import com.parkinglot.parkinglot.ParkingLot;
import com.parkinglot.ticketing.Ticket;
import com.parkinglot.vehicle.Vehicle;

public class Entrance {
    private final ParkingLot parkingLot;

    public Entrance(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket processEntry(Vehicle vehicle) {
        boolean isParked= parkingLot.parkVehicle(vehicle);
        if (!isParked) {
            return null;
        }
        return new Ticket(vehicle);
    }
}
