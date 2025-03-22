package com.parkinglot.payment;

public class MobileAppPayment implements PaymentStrategy {
    private final String mobileNo;

    public MobileAppPayment(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public void pay(double amount) {
        System.out.println("amount of $" + amount + " is paid through mobile app: " + mobileNo);
    }
}
