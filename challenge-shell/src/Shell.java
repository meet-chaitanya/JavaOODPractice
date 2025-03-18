import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
    private CommandHistory commandHistory = new CommandHistory();
    private CommandExecutor commandExecutor = new CommandExecutor(commandHistory);

    public void start() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String command;
            while (true) {
                System.out.print("ccsh>");
                command = br.readLine().trim();
                if (command.equalsIgnoreCase("exit")) {
                    commandHistory.saveHistory();
                    System.out.println("Exiting ccsh...");
                    break;
                }
                if (!command.isEmpty()) {
                    commandHistory.addCommand(command);
                }
                commandExecutor.execute(command);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Shell().start();
    }
}
