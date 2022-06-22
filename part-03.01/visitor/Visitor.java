package visitor;

import expression.BinaryExpression;
import expression.NumericConstant;
import expression.UnaryExpression;

/**
 * @author vaisakhvm
 */
public interface Visitor {
    double visit(NumericConstant number);
    double visit(BinaryExpression binaryExpression);
    double visit(UnaryExpression unaryExpression);
}
