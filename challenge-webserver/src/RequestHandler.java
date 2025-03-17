import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler implements Runnable{
    private final Socket clientSocket;
    private final String documentRoot;

    public RequestHandler(Socket clientSocket, String documentRoot) {
        this.clientSocket = clientSocket;
        this.documentRoot = documentRoot;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String requestLine = br.readLine();
            if (requestLine != null) {
                HttpRequestParser requestParser = new HttpRequestParser(requestLine);
                String path = requestParser.getPath();

                if (path.equals("/")) {
                    path = "/index.html";
                }

                System.out.println("Handling request for path: " + path + " | Thread ID: " + Thread.currentThread().getId());

                FileManager fileManager = new FileManager(documentRoot);
                if (SecurityManager.isValidRequest(path, documentRoot)) {
                    HttpResponse response = HttpResponseFactory.createResponse(path, fileManager);
                    response.send(writer);
                } else {
                    HttpResponse response = HttpResponseFactory.createResponseNotFound();
                    response.send(writer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling request due to: "+ e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client connection: " + e.getMessage());
            }
        }
    }
}
