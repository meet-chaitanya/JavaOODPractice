import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {
    private PatternMatchingStrategy patternMatchingStrategy;

    public Searcher(PatternMatchingStrategy patternMatchingStrategy) {
        this.patternMatchingStrategy = patternMatchingStrategy;
    }

    public void search(File file, String pattern, String excludePattern) {
        boolean foundMatch = false;
        List<String> matchedLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Pattern compilePattern = patternMatchingStrategy.compilePattern(pattern);
            while ((line = br.readLine()) != null) {
                Matcher matcher = compilePattern.matcher(line);
                if (matcher.find()) {
                    matchedLines.add(line);
                    foundMatch = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error searching through file: " + e.getMessage());
        }

        if (excludePattern != null) {
            matchedLines.removeIf(line -> line.contains(excludePattern));
        }

        for (String line: matchedLines) {
            System.out.println(file.getPath() + ":" + line);
        }

        if (!foundMatch) {
            System.exit(1);
        }
    }
}
