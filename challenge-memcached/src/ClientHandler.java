import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements Runnable{
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String sb = br.readLine() +
                    "\r\n" +
                    br.readLine();
            String[] commandParts = sb.split("\\s+");
            System.out.println("commandParts: " + Arrays.toString(commandParts));
            CommandLineArgsParser argsParser = new CommandLineArgsParser(commandParts);
            CommandExecutor commandExecutor = new CommandExecutor(argsParser);
            commandExecutor.execute(new PrintWriter(clientSocket.getOutputStream(), true));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
