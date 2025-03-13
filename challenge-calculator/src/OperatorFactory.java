import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    private Map<String, ExpressionEvaluator> operatorMap;

    public OperatorFactory() {
        operatorMap = new HashMap<>();
        operatorMap.put("+", new AdditionEvalutor());
        operatorMap.put("-", new SubstractionEvaluator());
        operatorMap.put("*", new MultiplicationEvaluator());
        operatorMap.put("/", new DivisionEvaluator());
    }
    public ExpressionEvaluator getExpressionEvaluator(String operator) {
        ExpressionEvaluator expressionEvaluator =  operatorMap.get(operator);
        if (expressionEvaluator == null) {
            throw new UnsupportedOperationException("operator not supported: " + operator);
        }
        return expressionEvaluator;
    }
}
