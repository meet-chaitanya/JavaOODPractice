import java.util.List;

public class Calculator {

    private  InfixToPostfixConverter infixToPostfixConverter;
    private OperatorFactory evaluatorFactory;
    private  PostfixEvaluator postfixEvaluator;

    public Calculator() {
        evaluatorFactory = new OperatorFactory();
        infixToPostfixConverter = new InfixToPostfixConverter();
        postfixEvaluator = new PostfixEvaluator(evaluatorFactory);
    }

    public double evaluateExpression(String expression) {
        List<String> postfix = infixToPostfixConverter.convertToPostfix(expression);
        return postfixEvaluator.evaluate(postfix);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Result: " + calculator.evaluateExpression("1 + 1 * 5"));
        System.out.println("Result: " + calculator.evaluateExpression("(1 +1 ) * 5"));
    }

}