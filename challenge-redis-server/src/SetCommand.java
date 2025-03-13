import java.io.PrintWriter;

public class SetCommand implements Command{
    private final DatabaseService databaseService;

    public SetCommand(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
    @Override
    public RESPMessage execute(Array array, PrintWriter out) {
        if (array.getValues().length < 3) {
            out.println("-ERR wrong number of arguments for SET");
            return null;
        }
        String key = ((BulkString)array.getValues()[1]).getValue();
        String value = ((BulkString)array.getValues()[2]).getValue();
        databaseService.set(key, value);

        return new SimpleString("OK");
    }
}
