package com.kc.linkedin.manager;

import com.kc.linkedin.model.Message;
import com.kc.linkedin.model.NotificationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {
    private Map<String, List<Message>> messagesByUser = new HashMap<>();
    private NotificationManager notificationManager;

    public MessageManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        Message message = new Message(senderId, receiverId, content);
        messagesByUser.computeIfAbsent(senderId, k -> new ArrayList<>()).add(message);
        messagesByUser.computeIfAbsent(receiverId, k -> new ArrayList<>()).add(message);
        notificationManager.sendNotification(receiverId, NotificationType.MESSAGE_RECEIVED,
                "Message received from " + senderId);
    }

    public List<Message> getMessages(String userId) {
        return messagesByUser.getOrDefault(userId, new ArrayList<>());
    }
}
