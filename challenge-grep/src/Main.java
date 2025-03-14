import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            CommandLineArgsParser argsParser = new CommandLineArgsParser(args);
            String path = argsParser.getPath();
            String pattern = argsParser.getPattern();
            String excludePattern = argsParser.getExcludePattern();
            boolean isRecursive = argsParser.isRecursive();
            boolean isCaseInsensitive = argsParser.isCaseInsensitive();

            PatternMatchingStrategy strategy = isCaseInsensitive ? new CaseInsensitiveStrategy() : new CaseSensitiveStrategy();

            SearchContext context = new SearchContext(strategy);

            File fileOrDir = new File(path);

            if (!fileOrDir.exists()) {
                System.out.println("The file or directory does not exist.");
                System.exit(3);
            }

            context.search(fileOrDir, pattern, excludePattern, isRecursive);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}