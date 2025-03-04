import java.util.List;
import java.util.stream.Collectors;

public class JsonArray implements JsonElement{
    List<JsonElement> elements;
    JsonArray(List<JsonElement> values) {
        this.elements = values;
    }
    @Override
    public Object getValue() {
        return elements.stream().map(JsonElement::getValue).collect(Collectors.toList());
    }
}
