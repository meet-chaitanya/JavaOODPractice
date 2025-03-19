import java.io.PrintWriter;

public class SetCommand implements MemCachedCommand{
    private final CommandLineArgsParser argsParser;
    private final StoreService storeService = StoreService.getInstance();

    public SetCommand(CommandLineArgsParser argsParser) {
        this.argsParser = argsParser;
    }

    @Override
    public void execute(PrintWriter writer) {
        if (!argsParser.getCommand().equalsIgnoreCase("set")) {
            System.err.println("Unknown command passed to set command:");
            return;
        }

        String key = argsParser.getKey();
        String value = argsParser.getDataBlock();
        int expiry = argsParser.getExpTime();

        storeService.add(key, value, expiry);

        writer.write("STORED");
        writer.flush();
    }
}
