public class CommandLineArgsParser {
    private final String originalFileName;
    private final String modifiedFileName;

    public CommandLineArgsParser(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java DiffTool <originalFileName> <modifiedFileName>");
        }
        this.originalFileName = args[0];
        this.modifiedFileName = args[1];
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getModifiedFileName() {
        return modifiedFileName;
    }
}
