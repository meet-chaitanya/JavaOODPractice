package com.kc.linkedin.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String passwordHash;
    private Profile profile;
    private Set<String> connections;

    public User(String userName, String email, String passwordHash) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.profile = new Profile();
        this.connections = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Set<String> getConnections() {
        return connections;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", profile=" + profile +
                ", connections=" + connections +
                '}';
    }
}
