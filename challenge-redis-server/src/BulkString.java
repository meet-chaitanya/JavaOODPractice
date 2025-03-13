public class BulkString implements RESPMessage{
    private String value;

    public BulkString(String value) {
        this.value = value;
    }

    @Override
    public String serialize() {
        if (value == null) {
            return "$-1\r\n";
        }
        return "$" + value.length() + "\r\n" + value + "\r\n";
    }

    public String getValue() {
        return value;
    }
}
