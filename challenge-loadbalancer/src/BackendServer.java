public class BackendServer {
    private String host;
    private int port;
    private boolean isHealthy;

    public BackendServer(String host, int port) {
        this.host = host;
        this.port = port;
        this.isHealthy = true;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public String getAddress() {
        return getHost() + ":" + getPort();
    }
}
