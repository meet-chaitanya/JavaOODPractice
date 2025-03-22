package com.parkinglot.ticketing;

import com.parkinglot.payment.PaymentStrategy;
import com.parkinglot.vehicle.Vehicle;

public class ParkingTransaction {
    private final PaymentStrategy paymentStrategy;

    public ParkingTransaction(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(Vehicle vehicle, long timeSpent) {
        double amountToPay = vehicle.calculateAmount(timeSpent);
        paymentStrategy.pay(amountToPay);
    }
}
