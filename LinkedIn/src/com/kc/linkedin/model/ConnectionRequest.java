package com.kc.linkedin.model;

public class ConnectionRequest {
    private String fromUserId;
    private String toUserId;
    private ConnectionStatus status;

    public ConnectionRequest(String fromUserId, String toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = ConnectionStatus.PENDING;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }
}
