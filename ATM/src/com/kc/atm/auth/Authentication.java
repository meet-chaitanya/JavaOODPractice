package com.kc.atm.auth;

import com.kc.atm.models.Account;
import com.kc.atm.models.User;

import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private final Map<String, User> userDB = new HashMap<>();

    public Authentication() {
        userDB.put("UserA", new User("UserA", "1234", new Account(1000)));
        userDB.put("user2", new User("user2", "5678", new Account(2500)));
    }

    public boolean authenticate(String userName, String pin) {
        User user = userDB.get(userName);
        return user != null && user.getPin().equals(pin);
    }

    public User getUser(String userName) {
        return userDB.get(userName);
    }
}
