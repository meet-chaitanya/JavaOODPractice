package com.kc.atm.transactions;

import com.kc.atm.models.User;

public class Withdrawal implements Transaction{
    @Override
    public void execute(User user, double amount) {
        if (user.getAccount().getBalance() >= amount) {
            user.getAccount().updateBalance(-amount);
            System.out.println("withdrew amount $" + amount);
        } else {
            System.out.println("Insufficient funds!!");
        }
    }
}
