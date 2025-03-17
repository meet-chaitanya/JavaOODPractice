import java.nio.file.Path;
import java.nio.file.Paths;

public class SecurityManager {

    public static boolean isValidRequest(String path, String documentRoot) {
        if (!path.startsWith("/")) {
            return false;
        }
        Path resolvedPath = Paths.get(documentRoot).resolve(path.substring(1)).normalize();

        if (resolvedPath.isAbsolute()) {
            return false;
        }

        return resolvedPath.startsWith(documentRoot);
    }
}
