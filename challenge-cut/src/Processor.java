import java.io.BufferedReader;
import java.io.IOException;

public class Processor {
    private InputSource inputSource;
    private FieldsExtractionStrategy strategy;

    public Processor(InputSource inputSource, FieldsExtractionStrategy strategy) {
        this.inputSource = inputSource;
        this.strategy = strategy;
    }

    public void process() throws IOException {
        try (BufferedReader br = inputSource.getReader()) {
            String line;
            while((line = br.readLine()) != null) {
                strategy.extractFields(line);
            }
        }
    }
}
