public class SimpleString implements RESPMessage{
    private String value;

    public SimpleString(String value) {
        this.value = value;
    }

    @Override
    public String serialize() {
        return "+" + value + "\r\n";
    }
}
