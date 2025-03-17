import java.io.PrintWriter;

public interface HttpResponse {
    void send(PrintWriter writer);
}
