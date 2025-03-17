import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class UniqueStrategy implements UniqStrategy{
    @Override
    public void process(Map<String, Integer> lineCountMap, BufferedWriter writer) throws IOException {
        for (String line: lineCountMap.keySet()) {
            if (lineCountMap.get(line) == 1) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
