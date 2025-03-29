public class Main {
    public static void main(String[] args) {
        LogConfig config = new LogConfig();
        config.setCurrentLogLevel(LogLevel.DEBUG);
        config.setCurrentLogDestination(LogDestination.CONSOLE);

        Logger logger = LoggerFactory.getLogger(config.getCurrentLogDestination(), config.getFilePath());
        logger.setLogLevel(config.getCurrentLogLevel());

        logger.log(LogLevel.DEBUG, "this is a debug message");
        logger.log(LogLevel.INFO, "this is a info message");
        logger.log(LogLevel.WARNING, "this is a warning message");
        logger.log(LogLevel.ERROR, "this is a error message");
        logger.log(LogLevel.FATAL, "this is a fatal message");
    }
}
