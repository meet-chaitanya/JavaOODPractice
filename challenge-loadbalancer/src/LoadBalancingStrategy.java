import java.util.List;

public interface LoadBalancingStrategy {
    BackendServer getNextServer(List<BackendServer> availableServers);
}
