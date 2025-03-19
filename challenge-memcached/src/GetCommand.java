import java.io.PrintWriter;

public class GetCommand implements MemCachedCommand{
    private final CommandLineArgsParser argsParser;
    private final StoreService storeService = StoreService.getInstance();

    public GetCommand(CommandLineArgsParser argsParser) {
        this.argsParser = argsParser;
    }

    @Override
    public void execute(PrintWriter writer) {
        if (!argsParser.getCommand().equalsIgnoreCase("get")) {
            System.err.println("Unknown command passed to get command:");
            return;
        }

        String value = storeService.get(argsParser.getKey());
        if (value == null) {
            writer.write("\r\n");
        } else {
            writer.write(value);
        }
        writer.flush();
    }
}
