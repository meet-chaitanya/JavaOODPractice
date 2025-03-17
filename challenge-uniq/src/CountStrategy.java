import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class CountStrategy implements UniqStrategy{

    @Override
    public void process(Map<String, Integer> lineCountMap, BufferedWriter writer) throws IOException {
        for (String line: lineCountMap.keySet()) {
            writer.write(lineCountMap.get(line) + " " + line);
            writer.newLine();
        }
    }
}
