package expression;

import lexer.Operator;

/**
 * @author vaisakhvm
 */
public class UnaryExpression extends Expression {
    private Expression leftExpression;
    private Operator operator;

    public UnaryExpression(Expression leftExpression, Operator operator) {
        this.leftExpression = leftExpression;
        this.operator = operator;
    }

    @Override
    public double evaluate(RuntimeContext context) {
        switch (operator) {
            case PLUS:
                return leftExpression.evaluate(context);
            case MINUS:
                return - leftExpression.evaluate(context);
        }
        return Double.NaN;
    }
}
