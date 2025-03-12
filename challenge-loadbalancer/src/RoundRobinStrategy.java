import java.util.List;

public class RoundRobinStrategy implements LoadBalancingStrategy{
    private int currentIndex = 0;

    @Override
    public synchronized BackendServer getNextServer(List<BackendServer> availableServers) {
        if (availableServers.isEmpty()) {
            throw new IllegalStateException("No available servers");
        }
        BackendServer server = availableServers.get(currentIndex);
        currentIndex = (currentIndex + 1) % availableServers.size();
        return server;
    }
}
