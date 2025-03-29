public class LogConfig {
    private LogLevel currentLogLevel = LogLevel.INFO;
    private LogDestination currentLogDestination = LogDestination.CONSOLE;
    private String filePath = "log.txt";

    public LogLevel getCurrentLogLevel() {
        return currentLogLevel;
    }

    public void setCurrentLogLevel(LogLevel currentLogLevel) {
        this.currentLogLevel = currentLogLevel;
    }

    public LogDestination getCurrentLogDestination() {
        return currentLogDestination;
    }

    public void setCurrentLogDestination(LogDestination currentLogDestination) {
        this.currentLogDestination = currentLogDestination;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
