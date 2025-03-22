package com.parkinglot.payment;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("amount of $" + amount + " is payed by cash.");
    }
}
