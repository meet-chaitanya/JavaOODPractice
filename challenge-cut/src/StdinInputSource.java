import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdinInputSource implements InputSource{
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
