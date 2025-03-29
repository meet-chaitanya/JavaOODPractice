public interface Logger {
    void log(LogLevel level, String msg);
    void setLogLevel(LogLevel level);
    void setLogDestination(LogDestination destination);
}
