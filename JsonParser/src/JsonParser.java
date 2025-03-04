import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    int index;
    String jsonString;

    private static final char OPEN_CURLY_BRACE = '{';
    private static final char CLOSE_CURLY_BRACE = '}';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char DOUBLE_QUOTE = '"';
    private static final char COMMA = ',';
    private static final char COLON = ':';

    public JsonElement parse(String jsonString) {
        this.index = 0;
        this.jsonString = jsonString;
        skipWhiteSpaces();
        return parseValue();
    }

    public JsonElement parseValue() {
        char currentChar = jsonString.charAt(index);
        if (currentChar == OPEN_CURLY_BRACE) {
            return parseObject();
        } else if (currentChar == OPEN_SQUARE_BRACKET) {
            return parseArray();
        } else if (currentChar == DOUBLE_QUOTE) {
            return parseString();
        } else if (Character.isDigit(currentChar) || currentChar == '-') {
            return parseNumber();
        } else if (currentChar == 't' || currentChar == 'f') {
            return parseBoolean();
        } else if (currentChar == 'n') {
            return parseNull();
        }
        throw new InvalidJsonException("parsing failed at " + index + " : " + jsonString.substring(0, index));
    }

    private void consume(char expected) {
        if (jsonString.charAt(index) == expected){
            index++;
        } else {
            throw new InvalidJsonException("parsing failed :" + jsonString.substring(0, index) + ": expected: " + expected);
        }
    }

    private void skipWhiteSpaces() {
        while (Character.isWhitespace(jsonString.charAt(index))) {
            index++;
        }
    }

    private JsonElement parseObject() {
        Map<String, JsonElement> properties = new HashMap<>();
        consume(OPEN_CURLY_BRACE);
        skipWhiteSpaces();
        while (jsonString.charAt(index) != CLOSE_CURLY_BRACE) {
            String propertyName = parseString().getValue().toString();
            skipWhiteSpaces();

            consume(COLON);
            skipWhiteSpaces();

            JsonElement propertyValue = parseValue();
            properties.put(propertyName, propertyValue);

            skipWhiteSpaces();

            if (jsonString.charAt(index) == COMMA) {
                consume(COMMA);
                skipWhiteSpaces();
            }
        }

        consume(CLOSE_CURLY_BRACE);
        return new JsonObject(properties);
    }

    private JsonElement parseArray() {
        List<JsonElement> elements = new ArrayList<>();
        consume(OPEN_SQUARE_BRACKET);
        skipWhiteSpaces();
        while (jsonString.charAt(index) != CLOSE_SQUARE_BRACKET) {
            JsonElement element = parseValue();
            elements.add(element);
            skipWhiteSpaces();
            if (jsonString.charAt(index) == COMMA) {
                consume(COMMA);
                skipWhiteSpaces();
            }
        }
        consume(CLOSE_SQUARE_BRACKET);
        return new JsonArray(elements);
    }

    private JsonElement parseString() {
        consume(DOUBLE_QUOTE);
        String word = consumeWord();
        consume(DOUBLE_QUOTE);
        return new JsonString(word);
    }

    private String consumeWord() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetter(jsonString.charAt(index))){
            sb.append(jsonString.charAt(index));
            index++;
        }
        return sb.toString();
    }

    private JsonElement parseNumber() {
        int startIndex = index;
        while (Character.isDigit(jsonString.charAt(index)) || jsonString.charAt(index) == '.') {
            index++;
        }
        String numberStr = jsonString.substring(startIndex, index);
        if (numberStr.contains(".")) {
            return new JsonNumber(Double.parseDouble(numberStr));
        }
        return new JsonNumber(Long.parseLong(numberStr));
    }

    private JsonElement parseBoolean() {
        String boolStr = consumeWord();
        if (boolStr.equals("true")) return new JsonBoolean(Boolean.TRUE);
        else if (boolStr.equals("false")) return new JsonBoolean(Boolean.FALSE);

        throw new InvalidJsonException("invalid boolean value");
    }

    private JsonElement parseNull() {
        consumeWord();
        return new JsonNull();
    }
}
