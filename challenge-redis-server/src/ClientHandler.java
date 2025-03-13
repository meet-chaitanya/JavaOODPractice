import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private CommandFactory commandFactory;

    public ClientHandler(Socket clientSocket, CommandFactory commandFactory) {
        this.clientSocket = clientSocket;
        this.commandFactory = commandFactory;
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                RESPMessage message = RESPParser.parse(line);
                if (message instanceof Array) {
                    Array array = (Array) message;
                    String commandName = ((BulkString) array.getValues()[0]).getValue();
                    Command command = commandFactory.getCommand(commandName);
                    if (command != null) {
                        RESPMessage response = command.execute(array, out);
                        if (response != null) {
                            out.print(response.serialize());
                            out.flush();
                        }
                    } else {
                        out.println("-ERR unknown command");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}
