import java.io.PrintWriter;

public interface Command {
    RESPMessage execute(Array array, PrintWriter out);
}
