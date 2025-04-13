package com.kc.linkedin.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private String id;
    private String userId;
    private NotificationType type;
    private String content;
    private boolean isRead;
    private LocalDateTime timestamp;

    public Notification(String userId, NotificationType type, String content) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.isRead = false;
        this.timestamp = LocalDateTime.now();
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
