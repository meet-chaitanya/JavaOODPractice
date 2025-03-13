import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixToPostfixConverter {
    private static Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
    private static Map<String, Integer> PRECEDENCE = new HashMap<>();

    static  {
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
    }

    public List<String> convertToPostfix(String expression) {
        List<String> postfix = new ArrayList<>();
        Stack<String> st = new Stack<>();

        expression = expression.replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("([0-9]+(?:\\.[0-9]+)?)|([\\+\\-\\*/\\(\\)])");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()){
            String val = matcher.group();
            if (isNumber(val)) {
                postfix.add(val);
            } else if (OPERATORS.contains(val)) {
                while (!st.empty() && precedence(st.peek()) >= precedence(val)) {
                    postfix.add(st.pop());
                }
                st.push(val);
            } else if (val.equals("(")) {
                st.push(val);
            } else if (val.equals(")")) {
                while (!st.empty() && !st.peek().equals("(")) {
                    postfix.add(st.pop());
                }
                st.pop();
            }
        }
        while (!st.empty()) {
            postfix.add(st.pop());
        }
        return postfix;
    }

    private int precedence(String val) {
        return PRECEDENCE.getOrDefault(val, 0);
    }

    private boolean isNumber(String val) {
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
