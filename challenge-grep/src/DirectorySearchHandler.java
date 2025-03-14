import java.io.File;

public class DirectorySearchHandler {
    private Searcher searcher;

    public DirectorySearchHandler(Searcher searcher) {
        this.searcher = searcher;
    }

    public void handleDirectory(File directory, String pattern, String excludePattern) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file: files) {
            if (file.isDirectory()) {
                handleDirectory(file, pattern, excludePattern);
            } else if (file.isFile()) {
                FileSearchHandler fileHandler = new FileSearchHandler(searcher);
                fileHandler.handleFile(file, pattern, excludePattern);
            }
        }
    }
}
