public class DivisionEvaluator implements ExpressionEvaluator{
    @Override
    public double evaluate(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("cant divide with zero");
        }
        return a / b;
    }
}
