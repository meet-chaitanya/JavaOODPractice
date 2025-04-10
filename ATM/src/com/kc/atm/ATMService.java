package com.kc.atm;

import com.kc.atm.auth.Authentication;
import com.kc.atm.hardware.CashDispenser;
import com.kc.atm.models.User;
import com.kc.atm.transactions.Transaction;
import com.kc.atm.transactions.Withdrawal;

public class ATMService {
    private final Authentication auth = new Authentication();
    private final CashDispenser dispenser = new CashDispenser();

    public boolean login(String userName, String pin) {
        return auth.authenticate(userName, pin);
    }

    public void checkBalance(String userName) {
        User user = auth.getUser(userName);
        System.out.println("Balance: $" + user.getAccount().getBalance());
    }

    public void processTransaction(String userName, double amount, Transaction transaction) {
        User user = auth.getUser(userName);
        transaction.execute(user, amount);
        if (transaction instanceof Withdrawal) {
            dispenser.dispense(amount);
        }
    }
}
