import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger extends AbstractLogger{
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeLog(String msg) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true))) {
            printWriter.println("FILE: " + msg);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
