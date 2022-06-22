package expression;

/**
 * @author vaisakhvm
 */
import lexer.Operator;
import visitor.Visitor;

public class BinaryExpression extends Expression {
    public Expression leftExpression, rightExpression;
    public Operator operator;

    public BinaryExpression(Expression a, Expression b, Operator op) {
        leftExpression = a;
        rightExpression = b;
        operator = op;
    }

    @Override
    public double accept(Visitor visitor) throws Exception {
        return visitor.visit(this);
    }
}