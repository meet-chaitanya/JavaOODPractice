import java.io.PrintWriter;

public class HttpResponseNotFound implements HttpResponse{
    @Override
    public void send(PrintWriter writer) {
        writer.write("HTTP/1.1 404 Not Found\r\n");
        writer.write("Content-Type: text/html\r\n");
        writer.write("\r\n");
        writer.write("<html><body><h1>404 Not Found</h1></body></html>");
        writer.flush();
    }
}
