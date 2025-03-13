import java.io.PrintWriter;

public class PingCommand implements Command{
    @Override
    public RESPMessage execute(Array array, PrintWriter out) {
        return new SimpleString("PONG");
    }
}
