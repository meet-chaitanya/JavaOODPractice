import java.util.ArrayList;
import java.util.List;

public class CommandLineArgsParser {
    private String[] args;
    private List<Integer> fieldNumbers;
    private String delimiter;
    private String filePath;

    public CommandLineArgsParser(String[] args) {
        this.args = args;
        this.fieldNumbers = new ArrayList<>();
        this.delimiter = "\t";
        this.filePath = null;
    }

    public void parse() {
        for (String arg: args) {
            if (arg.startsWith("-f")) {
                parseFieldList(arg.substring(2));
            } else if (arg.startsWith("-d")) {
                delimiter = arg.substring(2);
            } else {
                filePath = arg;
            }
        }
    }

    private void parseFieldList(String fieldStr) {
        String[] fields = fieldStr.split("[,\\s]+");
        for (String field: fields) {
            fieldNumbers.add(Integer.parseInt(field));
        }
    }

    public List<Integer> getFieldNumbers() {
        return fieldNumbers;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
