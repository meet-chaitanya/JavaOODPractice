public class JsonBoolean implements JsonElement{
    Boolean value;
    JsonBoolean(Boolean val) {
        this.value = val;
    }
    @Override
    public Object getValue() {
        return value;
    }
}
