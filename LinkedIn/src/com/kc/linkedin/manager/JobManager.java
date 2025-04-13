package com.kc.linkedin.manager;

import com.kc.linkedin.model.Job;
import com.kc.linkedin.model.NotificationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobManager {
    private Map<String, Job> jobs = new HashMap<>();
    private NotificationManager notificationManager;

    public JobManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public Job postJob(String employerId, String title, String description) {
        Job job = new Job(employerId, title, description);
        jobs.put(job.getId(), job);
        return job;
    }

    public void applyJob(String userId, String jobId) {
        Job job = jobs.get(jobId);
        if (job != null) {
            job.getApplicants().add(userId);
            notificationManager.sendNotification(job.getEmployerId(), NotificationType.JOB_APPLICATION,
                    "New application from user " + userId + " to job: " + job.getTitle());
        }
    }

    public List<Job> listJobs() {
        return new ArrayList<>(jobs.values());
    }
}
