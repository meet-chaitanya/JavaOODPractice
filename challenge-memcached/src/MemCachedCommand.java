import java.io.PrintWriter;

public interface MemCachedCommand {
    void execute(PrintWriter writer);
}
