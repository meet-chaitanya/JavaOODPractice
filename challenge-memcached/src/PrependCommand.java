import java.io.PrintWriter;

public class PrependCommand implements MemCachedCommand{
    private final CommandLineArgsParser argsParser;
    private final StoreService storeService = StoreService.getInstance();

    public PrependCommand(CommandLineArgsParser argsParser) {
        this.argsParser = argsParser;
    }

    @Override
    public void execute(PrintWriter writer) {
        if (!argsParser.getCommand().equalsIgnoreCase("prepend")) {
            System.err.println("Unknown command passed to prepend command:");
            return;
        }
        String key = argsParser.getKey();
        String value = argsParser.getDataBlock();
        int expiry = argsParser.getExpTime();

        if (storeService.prepend(key, value, expiry)) {
            writer.write("STORED");
        } else {
            writer.write("NOT_STORED");
        }
        writer.flush();
    }
}
