import java.util.ArrayList;
import java.util.List;

public class RESPParser {
    public static RESPMessage parse(String resp) {
        if (resp.startsWith("+")) {
            return new SimpleString(resp.substring(1, resp.length() - 2));
        } else if (resp.startsWith("-")) {
            return new Error(resp.substring(1, resp.length() - 2));
        } else if (resp.startsWith(":")) {
            return new IntegerReply(Integer.parseInt(resp.substring(1, resp.length() - 2)));
        } else if (resp.startsWith("$")) {
            if (resp.equals("$-1\r\n")) {
                return new BulkString(null);
            }
            int length = Integer.parseInt(resp.substring(1, resp.indexOf("\r\n")));
            String value = resp.substring(resp.indexOf("\r\n") + 2, resp.length() - 2);
            return new BulkString(value);
        } else if (resp.startsWith("*")) {
            int count = Integer.parseInt(resp.substring(1, resp.indexOf("\r\n")));
            List<RESPMessage> elements = new ArrayList<>();
            int index = resp.indexOf("\r\n") + 2;

            for (int i = 0; i < count; i++) {
                int nextElementStart = findNextElementStart(resp, index);
                String elementResp = resp.substring(index, nextElementStart);
                elements.add(parse(elementResp));
                index = nextElementStart;
            }
            return new Array(elements.toArray(new RESPMessage[0]));
        } else {
            throw new IllegalArgumentException("Invalid RESP Message");
        }
    }

    private static int findNextElementStart(String resp, int index) {
        int depth = 0;
        while (index < resp.length()) {
            if (resp.charAt(index) == '\r' && resp.charAt(index + 1) == '\n') {
                if (depth == 0) {
                    return index + 2;
                }
                depth--;
            } else if (resp.charAt(index) == '*' || resp.charAt(index) == '$') {
                depth++;
            }
            index++;
        }
        return index;
    }
}
