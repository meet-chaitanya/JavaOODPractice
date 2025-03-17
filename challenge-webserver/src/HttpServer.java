import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final int port = 80;
    private final String documentRoot;

    public HttpServer(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("server is listening on port: " + port);
            ExecutorService threadPool = Executors.newFixedThreadPool(10);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection request made from: " + clientSocket.getInetAddress());
                threadPool.submit(new RequestHandler(clientSocket, documentRoot));
            }
        } catch (IOException e) {
            System.err.println("Error caused due to: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String documentRoot = args.length > 0 ? args[0] : "www";
        HttpServer server = new HttpServer(documentRoot);
        server.start();
    }
}
