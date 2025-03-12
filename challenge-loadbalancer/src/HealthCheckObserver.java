public interface HealthCheckObserver {
    void onServerHealthChanged(BackendServer server, boolean isHealthy);
}
