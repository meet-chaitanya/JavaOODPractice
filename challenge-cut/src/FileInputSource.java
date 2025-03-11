import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputSource implements InputSource{
    private String filePath;

    public FileInputSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }
}
