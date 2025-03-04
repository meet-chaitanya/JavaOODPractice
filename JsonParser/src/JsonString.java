public class JsonString implements JsonElement{
    String value;
    JsonString(String val) {
        this.value = val;
    }
    @Override
    public Object getValue() {
        return value;
    }
}
