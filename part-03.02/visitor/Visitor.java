package visitor;

import expression.BinaryExpression;
import expression.NumericConstant;
import expression.UnaryExpression;

/**
 * @author vaisakhvm
 */
public interface Visitor {
    double visit(NumericConstant number) throws Exception;
    double visit(BinaryExpression binaryExpression) throws Exception;
    double visit(UnaryExpression unaryExpression) throws Exception;
}
