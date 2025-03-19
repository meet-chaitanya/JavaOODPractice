import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private Map<String, MemCachedCommand> commandMap = new HashMap<>();
    private CommandLineArgsParser argsParser;

    public CommandExecutor(CommandLineArgsParser argsParser) {
        this.argsParser = argsParser;
        commandMap.put("set", new SetCommand(argsParser));
        commandMap.put("get", new GetCommand(argsParser));
        commandMap.put("append", new AppendCommand(argsParser));
        commandMap.put("prepend", new PrependCommand(argsParser));
    }

    public void execute(PrintWriter writer) {
        MemCachedCommand command = commandMap.get(argsParser.getCommand());
        if (command == null) {
            throw new IllegalArgumentException("Invalid command passed: " + argsParser.getCommand());
        }
        command.execute(writer);
    }
}
