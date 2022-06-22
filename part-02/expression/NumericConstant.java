package expression;

/**
 * @author vaisakhvm
 */
public class NumericConstant extends Expression {
    private double value;

    public NumericConstant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(RuntimeContext context) {
        return value;
    }
}