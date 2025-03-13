public class IntegerReply implements RESPMessage{
    private int value;

    public IntegerReply(int value) {
        this.value = value;
    }

    @Override
    public String serialize() {
        return ":" + value + "\r\n";
    }
}
