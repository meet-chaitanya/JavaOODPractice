import java.io.BufferedReader;
import java.io.IOException;

public interface InputSource {
    BufferedReader getReader() throws IOException;
}
