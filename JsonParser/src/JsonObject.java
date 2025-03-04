import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonElement{
    Map<String, JsonElement> properties;
    JsonObject(Map<String, JsonElement> properties) {
        this.properties = properties;
    }
    @Override
    public Object getValue() {
        Map<String, Object> result = new HashMap<>();
        properties.forEach((key, value) -> {
            result.put(key, value.getValue());
        });
        return result;
    }
}
