public class HttpRequestParser {
    private final String path;

    public HttpRequestParser(String requestLine) {
        String[] requestParts = requestLine.split(" ");
        this.path = requestParts[1];
    }

    public String getPath() {
        return path;
    }
}
