import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemCached {
    private final int port = 11211;
    private final int threadPoolSize = 10;
    private ExecutorService executorService;

    public void start() {
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Memcached Server is listening on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Connection made from: " + clientSocket.getInetAddress());
                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MemCached().start();
    }
}