package com.kc.linkedin;

import com.kc.linkedin.manager.*;
import com.kc.linkedin.model.ConnectionStatus;
import com.kc.linkedin.model.Job;
import com.kc.linkedin.model.User;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        NotificationManager notificationManager = new NotificationManager();
        ConnectionManager connectionManager = new ConnectionManager(userManager, notificationManager);
        MessageManager messageManager = new MessageManager(notificationManager);
        JobManager jobManager = new JobManager(notificationManager);
        SearchManager searchManager = new SearchManager(userManager, jobManager);

        User alice = userManager.register("Alice", "alice@email", "123");
        User bob = userManager.register("Bob", "bob@email", "456");

        connectionManager.connectionRequest(alice.getUserId(), bob.getUserId());
        connectionManager.respondToConnectionRequest(alice.getUserId(), bob.getUserId(), ConnectionStatus.ACCEPTED);

        messageManager.sendMessage(alice.getUserId(), bob.getUserId(), "Hi Bob!");

        notificationManager.getUnreadNotifications(bob.getUserId()).forEach(n -> System.out.println(n.getType() + " : " + n.getContent()));

        messageManager.getMessages(alice.getUserId()).forEach(System.out::println);

        Job job = jobManager.postJob(bob.getUserId(), "Backend Developer", "Java, Mysql, Mongo");
        jobManager.applyJob(alice.getUserId(), job.getId());

        searchManager.searchUsersByName("Alice").forEach(System.out::println);
        searchManager.searchJobsByTitle("Backend Developer").forEach(System.out::println);
    }
}
