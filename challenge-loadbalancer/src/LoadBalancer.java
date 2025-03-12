import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadBalancer implements HealthCheckObserver{

    private List<BackendServer> backendServers = new ArrayList<>();
    private List<BackendServer> availableServers = new ArrayList<>();
    private LoadBalancingStrategy loadBalancingStrategy;
    private ExecutorService executorService;
    private long healthCheckInterval;

    public LoadBalancer(List<String> backendServerAddresses, long healthCheckInterval) {
        for (String address: backendServerAddresses) {
            String[] serverPart = address.split(":", 2);
            String host = serverPart[0];
            int port = Integer.parseInt(serverPart[1]);
            BackendServer backendServer = new BackendServer(host, port);
            backendServers.add(backendServer);
            availableServers.add(backendServer);
        }
        this.loadBalancingStrategy = new RoundRobinStrategy();
        this.healthCheckInterval = healthCheckInterval;
    }

    public void start() {
        executorService = Executors.newCachedThreadPool();
        HealthCheckTask healthCheckTask = new HealthCheckTask(backendServers, this, healthCheckInterval);
        executorService.submit(healthCheckTask);

        int port = 80;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("LoadBalancer is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> handleClientRequest(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientRequest(Socket clientSocket) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder request = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                request.append(line).append("\r\n");
            }

            System.out.println("Request received:\n" + request.toString());

            BackendServer server = loadBalancingStrategy.getNextServer(availableServers);
            System.out.println(server.getHost() + " : " + server.getPort());
            try (Socket serverSocket = new Socket(server.getHost(), server.getPort())) {
                PrintWriter serverWriter = new PrintWriter(serverSocket.getOutputStream(), true);
                serverWriter.print(request.toString());
                serverWriter.flush();

                BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((line = serverReader.readLine()) != null) {
                    response.append(line).append("\r\n");
                }

                try {
                    PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                    clientWriter.print(response.toString());
                    clientWriter.flush();
                } catch (IOException e) {
                    System.err.println("Error sending response to client: " + e.getMessage());
                }

            } catch (IOException e) {
                System.err.println("Error connecting to backend server " + server.getAddress() + ": " + e.getMessage());
            }
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error processing client request: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<String> backendServers = Arrays.asList("localhost:8080", "localhost:8081", "localhost:8082");
        LoadBalancer loadBalancer = new LoadBalancer(backendServers, 5000);
        loadBalancer.start();
    }


    @Override
    public void onServerHealthChanged(BackendServer server, boolean isHealthy) {
        if (isHealthy) {
            availableServers.add(server);
        } else {
            availableServers.remove(server);
        }
    }
}