import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Sort {
    public static void main(String[] args) {
        CommandLineArgsParser argsParser = new CommandLineArgsParser(args);

        boolean uniqueFlag = argsParser.isUniqueFlag();
        String algorithm = argsParser.getAlgorithm();
        String fileName = argsParser.getFileName();

        List<String> lines = loadFile(fileName);

        SortingStrategy sortingStrategy = SortFactory.getSortingStrategy(algorithm);
        sortingStrategy.sort(lines);

        if (uniqueFlag) {
            Set<String> uniqueLines = new LinkedHashSet<>(lines);
            lines.clear();
            lines.addAll(uniqueLines);
        }

        int count = 0;
        for (String line: lines) {
            if (count >= 5) {
                break;
            }
            System.out.println(line);
            count++;
        }
    }

    private static List<String> loadFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lines;
    }
}