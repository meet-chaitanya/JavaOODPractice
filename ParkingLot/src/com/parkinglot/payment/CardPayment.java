package com.parkinglot.payment;

public class CardPayment implements PaymentStrategy {
    private final String cardNo;

    public CardPayment(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public void pay(double amount) {
        System.out.println("amount of $" + amount + "is paid through card: " + cardNo);
    }
}
