import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractLogger implements Logger{
    private LogLevel currentLoglevel = LogLevel.INFO;
    private LogDestination logDestination = LogDestination.CONSOLE;


    public abstract void writeLog(String msg);

    @Override
    public void log(LogLevel level, String msg) {
        if (level.ordinal() >= currentLoglevel.ordinal()) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logMessage = String.format("[%s] %s: %s", timestamp, level, msg);
            writeLog(logMessage);
        }
    }

    @Override
    public void setLogLevel(LogLevel level) {
        this.currentLoglevel = level;
    }

    @Override
    public void setLogDestination(LogDestination destination) {
        this.logDestination = destination;
    }
}
