public class CommandLineArgsParser {
    private String path;
    private String pattern = null;
    private String excludePattern = null;
    private boolean isCaseInsensitive = false;
    private boolean isRecursive = false;

    public CommandLineArgsParser(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java Main <pattern> <file/directory> [-r] [-v (exclude pattern) [-i]]");
        }

        pattern = args[0];
        path = args[1];
        for (int i = 2; i < args.length; i++) {
            if (args[i].equals("-r")) {
                isRecursive = true;
            } else if (args[i].equals("-v") && i + 1 < args.length) {
                excludePattern = args[++i];
            } else if (args[i].equals("-i")) {
                isCaseInsensitive = true;
            }
        }
    }

    public String getPath() {
        return path;
    }

    public boolean isRecursive() {
        return isRecursive;
    }

    public boolean isCaseInsensitive() {
        return isCaseInsensitive;
    }

    public String getExcludePattern() {
        return excludePattern;
    }

    public String getPattern() {
        return pattern;
    }
}
