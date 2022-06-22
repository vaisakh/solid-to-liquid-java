package expression;

import lexer.Operator;
import visitor.Visitor;

/**
 * @author vaisakhvm
 */
public class UnaryExpression extends Expression {
    public Expression leftExpression;
    public Operator operator;

    public UnaryExpression(Expression leftExpression, Operator operator) {
        this.leftExpression = leftExpression;
        this.operator = operator;
    }

    @Override
    public double accept(Visitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
