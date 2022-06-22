package expression;

/**
 * @author vaisakhvm
 */
import lexer.Operator;

public class BinaryExpression extends Expression {
    private Expression leftExpression, rightExpression;
    private Operator operator;

    public BinaryExpression(Expression a, Expression b, Operator op) {
        leftExpression = a;
        rightExpression = b;
        operator = op;
    }

    @Override
    public double evaluate(RuntimeContext context) {
        switch (operator) {
            case PLUS:
                return leftExpression.evaluate(context) + rightExpression.evaluate(context);
            case MINUS:
                return leftExpression.evaluate(context) - rightExpression.evaluate(context);
            case DIV:
                return leftExpression.evaluate(context) / rightExpression.evaluate(context);
            case MUL:
                return leftExpression.evaluate(context) * rightExpression.evaluate(context);
        }
        return Double.NaN;
    }
}