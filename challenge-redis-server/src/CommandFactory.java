import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final DatabaseService databaseService;
    private Map<String, Command> commandMap;

    public CommandFactory(DatabaseService databaseService){
        this.databaseService = databaseService;
        commandMap = new HashMap<>();
        commandMap.put("ping", new PingCommand());
        commandMap.put("set", new SetCommand(databaseService));
    }

    public Command getCommand(String commandName) {
        Command command = commandMap.get(commandName.toLowerCase());
        if (command == null) {
            throw new UnsupportedOperationException("Unsupported command: "+ commandName);
        }
        return command;
    }
}
