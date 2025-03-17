import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Processor {
    private InputSource inputSource;
    private UniqStrategy strategy;

    public Processor(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public void setStrategy(UniqStrategy strategy) {
        this.strategy = strategy;
    }

    public void process() {
        try (BufferedReader br = inputSource.getReader();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))){
            String line;
            Map<String, Integer> lineCountMap = new HashMap<>();
            while ((line = br.readLine()) != null) {
                lineCountMap.put(line, lineCountMap.getOrDefault(line, 0) + 1);
            }
            strategy.process(lineCountMap, writer);

        } catch (IOException e) {
            System.err.println("Error processing.." + e.getMessage());
        }
    }
}
