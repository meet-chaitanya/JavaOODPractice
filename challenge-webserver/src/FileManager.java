import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private final String documentRoot;

    public FileManager(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public Path getFile(String path) {
        Path filePath = Paths.get(documentRoot).resolve(path.substring(1)).normalize();
        return filePath.startsWith(documentRoot) ? filePath : null;
    }

    public String readFile(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
