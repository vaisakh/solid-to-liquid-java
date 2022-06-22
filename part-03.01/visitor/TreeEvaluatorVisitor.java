package visitor;

import expression.BinaryExpression;
import expression.NumericConstant;
import expression.UnaryExpression;

/**
 * @author vaisakhvm
 */
public class TreeEvaluatorVisitor implements Visitor {
    @Override
    public double visit(NumericConstant number) {
       return number.getValue();
    }

    @Override
    public double visit(BinaryExpression binaryExpression) {
        switch (binaryExpression.operator) {
            case PLUS:
                return binaryExpression.leftExpression.accept(this) + binaryExpression.rightExpression.accept(this);
            case MINUS:
                return binaryExpression.leftExpression.accept(this) - binaryExpression.rightExpression.accept(this);
            case DIV:
                return binaryExpression.leftExpression.accept(this) / binaryExpression.rightExpression.accept(this);
            case MUL:
                return binaryExpression.leftExpression.accept(this) * binaryExpression.rightExpression.accept(this);
        }
        return Double.NaN;
    }

    @Override
    public double visit(UnaryExpression unaryExpression) {
        switch (unaryExpression.operator) {
            case PLUS:
                return unaryExpression.leftExpression.accept(this);
            case MINUS:
                return - unaryExpression.leftExpression.accept(this);
        }
        return Double.NaN;
    }
}
