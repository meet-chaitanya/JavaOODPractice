package com.kc.linkedin.manager;

import com.kc.linkedin.model.Profile;
import com.kc.linkedin.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> usersById = new HashMap<>();
    private Map<String, User> usersByEmail = new HashMap<>();

    public User register(String username, String email, String password) {
        if (usersByEmail.containsKey(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        User user = new User(username, email, password);
        usersById.put(user.getUserId(), user);
        usersByEmail.put(email, user);
        return user;
    }

    public User login(String email, String password) {
        User user = usersByEmail.get(email);
        if (user == null || !user.getPasswordHash().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }

    public void updateProfile(String userId, Profile profile) {
        User user = usersById.get(userId);
        if (user != null) {
            user.setProfile(profile);
        }
    }

    public User getUserById(String userId) {
        return usersById.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(usersById.values());
    }
}
