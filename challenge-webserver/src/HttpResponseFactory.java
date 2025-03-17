import java.nio.file.Files;
import java.nio.file.Path;

public class HttpResponseFactory {

    public static HttpResponse createResponse(String path, FileManager fileManager) {
        Path filePath = fileManager.getFile(path);

        if (filePath != null && Files.exists(filePath)) {
            String contentType = getContentType(path);
            String content = fileManager.readFile(filePath);
            return new HttpResponseSuccess(content, contentType);
        } else {
            return createResponseNotFound();
        }
    }

    public static HttpResponse createResponseNotFound() {
        return new HttpResponseNotFound();
    }

    private static String getContentType(String path) {
        if (path.endsWith(".html")) {
            return "text/html";
        } else if (path.endsWith(".css")) {
            return "text/css";
        } else if (path.endsWith(".js")) {
            return "application/javascript";
        }
        return "text/plain";
    }
}
