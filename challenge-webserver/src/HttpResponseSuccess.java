import java.io.PrintWriter;

public class HttpResponseSuccess implements HttpResponse{
    private final String content;
    private final String contentType;

    public HttpResponseSuccess(String content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    @Override
    public void send(PrintWriter writer) {
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: " + contentType + "\r\n");
        writer.write("\r\n");
        writer.write(content);
        writer.flush();
    }
}
