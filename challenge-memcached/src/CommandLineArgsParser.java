public class CommandLineArgsParser {
    private String command;
    private String key;
    private double flags;
    private int expTime;
    private int byteCount;
    private String noReply;
    private String dataBlock;


    public CommandLineArgsParser(String[] args) {
        if (args[0].equalsIgnoreCase("get")) {
            if (args.length < 2) throw new IllegalArgumentException("Usage: get <key>");
            command = args[0];
            key = args[1];
            return;
        }
        if (args[0].equalsIgnoreCase("set") && args.length < 6) {
            throw new IllegalArgumentException("Usage: set <key> <flags> <expTime> <byteCount> <noReply?> <dataBlock>");
        }
        command = args[0];
        key = args[1];
        flags = Double.parseDouble(args[2]);
        expTime = Integer.parseInt(args[3]);
        byteCount = Integer.parseInt(args[4]);
        noReply = args[5].equalsIgnoreCase("noreply") ? args[5] : null;
        dataBlock = (noReply == null) ? args[5] : args[6];

        if (dataBlock.length() != byteCount) {
            throw new IllegalArgumentException("invalid data block size: " + dataBlock + " It should be: " + byteCount);
        }
    }

    public String getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public double getFlags() {
        return flags;
    }

    public int getExpTime() {
        return expTime;
    }

    public int getByteCount() {
        return byteCount;
    }

    public String getNoReply() {
        return noReply;
    }

    public String getDataBlock() {
        return dataBlock;
    }
}
