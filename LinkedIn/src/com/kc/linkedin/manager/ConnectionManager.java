package com.kc.linkedin.manager;

import com.kc.linkedin.model.ConnectionRequest;
import com.kc.linkedin.model.ConnectionStatus;
import com.kc.linkedin.model.NotificationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConnectionManager {
    private List<ConnectionRequest> requests = new ArrayList<>();
    private UserManager userManager;
    private NotificationManager notificationManager;

    public ConnectionManager(UserManager userManager, NotificationManager notificationManager) {
        this.userManager = userManager;
        this.notificationManager = notificationManager;
    }

    public void connectionRequest(String fromUserId, String toUserId) {
        requests.add(new ConnectionRequest(fromUserId, toUserId));
        notificationManager.sendNotification(toUserId, NotificationType.CONNECTION_REQUEST,
                "connection request received from " + userManager.getUserById(fromUserId).getUserName());
    }

    public void respondToConnectionRequest(String fromUserId, String toUserId, ConnectionStatus status) {
        for (ConnectionRequest request: requests) {
            if (request.getFromUserId().equals(fromUserId)
            && request.getToUserId().equals(toUserId)
            && request.getStatus().equals(ConnectionStatus.PENDING)) {
                request.setStatus(status);
                if (request.getStatus().equals(ConnectionStatus.ACCEPTED)) {
                    getUserConnections(fromUserId).add(toUserId);
                    getUserConnections(toUserId).add(fromUserId);
                    notificationManager.sendNotification(fromUserId, NotificationType.CONNECTION_ACCEPTED,
                            "connection accepted by " + userManager.getUserById(toUserId).getUserName());
                }
            }
        }
    }

    public Set<String> getUserConnections(String userId) {
        return userManager.getUserById(userId).getConnections();
    }
}
