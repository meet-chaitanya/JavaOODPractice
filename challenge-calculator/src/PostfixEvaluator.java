import java.util.List;
import java.util.Stack;

public class PostfixEvaluator {
    private OperatorFactory evaluatorFactory;

    public PostfixEvaluator(OperatorFactory evaluatorFactory) {
        this.evaluatorFactory = evaluatorFactory;
    }

    public double evaluate(List<String> postfix) {
        Stack<Double> st = new Stack<>();
        for (String val: postfix) {
            if (isNumber(val)) {
                st.push(Double.parseDouble(val));
            } else {
                double operand2 = st.pop();
                double operand1 = st.pop();
                ExpressionEvaluator operator = evaluatorFactory.getExpressionEvaluator(val);
                double result = operator.evaluate(operand1, operand2);
                st.push(result);
            }
        }
        return st.peek();
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
