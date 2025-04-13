package com.kc.linkedin.manager;

import com.kc.linkedin.model.Job;
import com.kc.linkedin.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SearchManager {
    private UserManager userManager;
    private JobManager jobManager;

    public SearchManager(UserManager userManager, JobManager jobManager) {
        this.userManager = userManager;
        this.jobManager = jobManager;
    }

    public List<User> searchUsersByName(String name) {
        return userManager.getAllUsers().stream()
                .filter(u -> u.getUserName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Job> searchJobsByTitle(String title) {
        return jobManager.listJobs().stream()
                .filter(j -> j.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}
