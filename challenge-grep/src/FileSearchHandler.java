import java.io.File;

public class FileSearchHandler {
    private Searcher searcher;

    public FileSearchHandler(Searcher searcher) {
        this.searcher = searcher;
    }

    public void handleFile(File file, String pattern, String excludePattern) {
        searcher.search(file, pattern, excludePattern);
    }
}
