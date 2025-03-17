import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public interface UniqStrategy {
    void process(Map<String, Integer> lineCountMap, BufferedWriter writer) throws IOException;
}
