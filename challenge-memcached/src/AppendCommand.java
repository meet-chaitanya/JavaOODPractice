import java.io.PrintWriter;

public class AppendCommand implements MemCachedCommand{
    private final CommandLineArgsParser argsParser;
    private final StoreService storeService = StoreService.getInstance();

    public AppendCommand(CommandLineArgsParser argsParser) {
        this.argsParser = argsParser;
    }

    @Override
    public void execute(PrintWriter writer) {
        if (!argsParser.getCommand().equalsIgnoreCase("append")) {
            System.err.println("Unknown command passed to append command:");
            return;
        }
        String key = argsParser.getKey();
        String value = argsParser.getDataBlock();
        int expiry = argsParser.getExpTime();

        if (storeService.append(key, value, expiry)) {
            writer.write("STORED");
        } else {
            writer.write("NOT_STORED");
        }
        writer.flush();
    }
}
