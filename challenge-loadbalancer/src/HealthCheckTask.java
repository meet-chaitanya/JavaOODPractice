import java.util.List;

public class HealthCheckTask implements Runnable{
    private List<BackendServer> backendServers;
    private HealthCheckObserver observer;
    private long healthCheckInterval;

    public HealthCheckTask(List<BackendServer> backendServers, HealthCheckObserver observer, long healthCheckInterval) {
        this.backendServers = backendServers;
        this.observer = observer;
        this.healthCheckInterval = healthCheckInterval;
    }

    @Override
    public void run() {
        while (true) {
            for (BackendServer server: backendServers) {
                boolean isHealthy = new HealthCheck(server).check();
                if (server.isHealthy() != isHealthy) {
                    observer.onServerHealthChanged(server, isHealthy);
                }
            }
            try {
                Thread.sleep(healthCheckInterval * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
