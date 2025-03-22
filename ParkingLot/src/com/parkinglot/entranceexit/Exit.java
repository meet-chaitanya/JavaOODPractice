package com.parkinglot.entranceexit;

import com.parkinglot.parkinglot.ParkingLot;
import com.parkinglot.payment.PaymentStrategy;
import com.parkinglot.ticketing.ParkingTransaction;
import com.parkinglot.ticketing.Ticket;
import com.parkinglot.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class Exit {
    private final ParkingLot parkingLot;

    public Exit(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void processExit(Ticket ticket, Vehicle vehicle, PaymentStrategy paymentStrategy) {
        if (ticket == null) {
            System.err.println("Ticket cant be null.");
            return;
        }

        long timeSpent = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toMinutes();

        ParkingTransaction transaction = new ParkingTransaction(paymentStrategy);
        transaction.processPayment(vehicle, timeSpent);

        boolean isUnParked = parkingLot.unParkVehicle(vehicle);

        if (!isUnParked) {
            System.out.println("Payment not processed, Please try again!");
            return;
        }
        System.out.println("Vehicle: " + vehicle.getLicensePlate() + " is processed for exit, it can leave now.");
    }
}
