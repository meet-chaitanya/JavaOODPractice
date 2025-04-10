package com.kc.atm.transactions;

import com.kc.atm.models.User;

public class Deposit implements Transaction{
    @Override
    public void execute(User user, double amount) {
        user.getAccount().updateBalance(amount);
        System.out.println("Deposited amount $" + amount);
    }
}
