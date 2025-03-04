public class JsonNumber implements JsonElement{
    Number value;
    JsonNumber(Number val){
        this.value = val;
    }
    @Override
    public Object getValue() {
        return value;
    }
}
