public class CommandLineArgsParser {
    private boolean unique;
    private boolean repeated;
    private boolean count;
    private String filePath;

    public CommandLineArgsParser(String[] args) {
        for(String arg: args) {
            switch(arg) {
                case "-c":
                case "--count":
                    count = true;
                    break;
                case "-d":
                    repeated = true;
                    break;
                case "-u":
                    unique = true;
                    break;
                default:
                    filePath = arg;
                    break;
            }
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isCount() {
        return count;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public boolean isUnique() {
        return unique;
    }
}
