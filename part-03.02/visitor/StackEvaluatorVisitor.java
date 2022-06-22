package visitor;

import expression.BinaryExpression;
import expression.NumericConstant;
import expression.UnaryExpression;
import stack.ValueStack;

/**
 * @author vaisakhvm
 */
public class StackEvaluatorVisitor implements Visitor {
    private ValueStack valueStack = new ValueStack();

    public StackEvaluatorVisitor() {
        valueStack.clear();
    }

    public double getValue() throws Exception {
        return valueStack.pop();
    }

    @Override
    public double visit(NumericConstant number) throws Exception {
        valueStack.push(number.getValue());
        return 0;
    }

    @Override
    public double visit(BinaryExpression binaryExpression) throws Exception {
        double x, y;
        binaryExpression.leftExpression.accept(this);
        binaryExpression.rightExpression.accept(this);

        switch (binaryExpression.operator) {
            case PLUS:
                x = valueStack.pop();
                y = valueStack.pop();
                valueStack.push(x + y);
                break;
            case MINUS:
                x = valueStack.pop();
                y = valueStack.pop();
                valueStack.push(y - x);
                break;
            case DIV:
                x = valueStack.pop();
                y = valueStack.pop();
                valueStack.push(y / x);
                break;
            case MUL:
                x = valueStack.pop();
                y = valueStack.pop();
                valueStack.push(x * y);
                break;
        }
        return Double.NaN;
    }

    @Override
    public double visit(UnaryExpression unaryExpression) throws Exception {
        unaryExpression.leftExpression.accept(this);
        switch (unaryExpression.operator) {
            case PLUS:
                valueStack.push(valueStack.pop());
            case MINUS:
                valueStack.push(-valueStack.pop());
        }
        return Double.NaN;
    }
}
