import java.util.Objects;

public class CommandLineArgsParser {
    private boolean uniqueFlag;
    private String algorithm;
    private String fileName;

    public CommandLineArgsParser(String[] args) {
        uniqueFlag = false;
        algorithm = "quick";
        parseArguments(args);
    }

    private void parseArguments(String[] args) {
        int index = 0;
        if (args.length > 1 && Objects.equals(args[index], "-u")) {
            uniqueFlag = true;
            index++;
        }

        if (args.length > index + 1 && Objects.equals(args[index], "-a")) {
            algorithm = args[index + 1];
            index += 2;
        }

        if (args.length > index) {
            fileName = args[index];
        } else {
            throw new IllegalArgumentException("FileName is required!!");
        }
    }

    public boolean isUniqueFlag() {
        return uniqueFlag;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getFileName() {
        return fileName;
    }
}
