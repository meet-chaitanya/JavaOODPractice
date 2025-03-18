import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private Map<String, ShellCommand> commandMap = new HashMap<>();;

    public CommandExecutor(CommandHistory history) {
        commandMap.put("pwd", new PwdCommand());
        commandMap.put("cd", new CdCommand());
        commandMap.put("history", new HistoryCommand(history));
    }

    public void execute(String input) {
        String[] parts = input.split("\\s+");
        String cmd = parts[0];
        if (commandMap.containsKey(cmd)) {
            ShellCommand shellCommand = commandMap.get(cmd);
            shellCommand.execute(Arrays.copyOfRange(parts, 1, parts.length));
        } else {
            executeExternalCommand(parts);
        }
    }

    private void executeExternalCommand(String[] commandArgs) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commandArgs);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
