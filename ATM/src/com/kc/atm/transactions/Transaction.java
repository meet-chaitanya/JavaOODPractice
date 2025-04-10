package com.kc.atm.transactions;

import com.kc.atm.models.User;

public interface Transaction {
    void execute(User user, double amount);
}
