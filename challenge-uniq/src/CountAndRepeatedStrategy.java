import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class CountAndRepeatedStrategy implements UniqStrategy{
    @Override
    public void process(Map<String, Integer> lineCountMap, BufferedWriter writer) throws IOException {
        for (String line: lineCountMap.keySet()) {
            if (lineCountMap.get(line) > 1) {
                writer.write(lineCountMap.get(line) + " " + line);
                writer.newLine();
            }
        }
    }
}
