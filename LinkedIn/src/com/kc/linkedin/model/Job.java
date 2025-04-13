package com.kc.linkedin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Job {
    private String id;
    private String employerId;
    private String title;
    private String description;
    private List<String> applicants;

    public Job(String employerId, String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.applicants = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getApplicants() {
        return applicants;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", employerId='" + employerId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", applicants=" + applicants +
                '}';
    }
}
