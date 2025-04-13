package com.kc.linkedin.manager;

import com.kc.linkedin.model.Notification;
import com.kc.linkedin.model.NotificationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationManager {
    private Map<String, List<Notification>> notificationsByUser = new HashMap<>();

    public void sendNotification(String userId, NotificationType type, String content) {
        Notification notification = new Notification(userId, type, content);
        notificationsByUser.computeIfAbsent(userId, k -> new ArrayList<>()).add(notification);
    }

    public List<Notification> getUnreadNotifications(String userId) {
        return notificationsByUser.get(userId).stream()
                .filter(n -> !n.isRead())
                .toList();
    }

    public List<Notification> getAllNotifications(String userId) {
        return notificationsByUser.getOrDefault(userId, new ArrayList<>());
    }

    public void markAllAsRead(String userId) {
        notificationsByUser.getOrDefault(userId, new ArrayList<>())
                .forEach(Notification::markAsRead);
    }
}
