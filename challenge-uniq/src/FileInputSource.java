import java.io.*;

public class FileInputSource implements InputSource{
    private final String filePath;

    public FileInputSource(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }
}
