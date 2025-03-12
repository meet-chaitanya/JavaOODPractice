import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HealthCheck {
    private BackendServer server;

    public HealthCheck(BackendServer server) {
        this.server = server;
    }

    public boolean check() {
        try {
            URL url = new URL("http", server.getHost(), server.getPort(), "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
