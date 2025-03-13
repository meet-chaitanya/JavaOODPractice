public class Error implements RESPMessage{
    private String value;

    public Error(String  value) {
        this.value = value;
    }

    @Override
    public String serialize() {
        return "-" + value + "\r\n";
    }
}
