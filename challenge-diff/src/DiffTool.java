import java.io.IOException;
import java.util.List;

public class DiffTool {

    public static void main(String[] args) {
        try {
            CommandLineArgsParser argsParser = new CommandLineArgsParser(args);

            String originalFileName = argsParser.getOriginalFileName();
            String newFileName = argsParser.getModifiedFileName();

            FileReader fileReader = new FileReader();

            List<String> originalLines = fileReader.readFile(originalFileName);
            List<String> modifiedLines = fileReader.readFile(newFileName);

            DiffGenerator diffGenerator = new DiffGenerator(new LCSDiffStrategy());

            List<String> diff = diffGenerator.generateDiff(originalLines, modifiedLines);

            DiffOutputFormatter outputFormatter = new DiffOutputFormatter();
            outputFormatter.printDiff(diff);

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
