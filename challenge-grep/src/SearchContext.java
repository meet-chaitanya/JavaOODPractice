import java.io.File;

public class SearchContext {
    private Searcher searcher;
    private FileSearchHandler fileSearchHandler;
    private DirectorySearchHandler directorySearchHandler;

    public SearchContext(PatternMatchingStrategy strategy) {
        searcher = new Searcher(strategy);
        fileSearchHandler = new FileSearchHandler(searcher);
        directorySearchHandler = new DirectorySearchHandler(searcher);
    }

    public void search(File file, String pattern, String excludePattern, boolean isRecursive) {
        if (isRecursive && file.isDirectory()) {
            directorySearchHandler.handleDirectory(file, pattern, excludePattern);
        } else if (file.isFile()) {
            fileSearchHandler.handleFile(file, pattern, excludePattern);
        }
    }
}
