package com.parkinglot.ticketing;

import com.parkinglot.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketNo;
    private final LocalDateTime entryTime;
    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        this.ticketNo = UUID.randomUUID().toString();;
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
