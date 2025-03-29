public class LoggerFactory {

    public static Logger getLogger(LogDestination logDestination, String filePath) {
        switch (logDestination) {
            case CONSOLE -> {
                return new ConsoleLogger();
            }
            case FILE -> {
                return new FileLogger(filePath);
            }
            case DATABASE -> {
                return new DatabaseLogger();
            }
            default -> {
                throw new IllegalArgumentException("unknown log destination" + logDestination);
            }
        }
    }
}
