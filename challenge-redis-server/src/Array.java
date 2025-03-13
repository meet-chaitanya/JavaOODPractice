public class Array implements RESPMessage{
    private RESPMessage[] values;

    public Array(RESPMessage[] values) {
        this.values = values;
    }

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("*").append(values.length).append("\r\n");
        for (RESPMessage val: values) {
            sb.append(val.serialize());
        }
        return sb.toString();
    }

    public RESPMessage[] getValues() {
        return values;
    }
}
