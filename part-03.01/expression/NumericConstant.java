package expression;

import visitor.Visitor;

/**
 * @author vaisakhvm
 */
public class NumericConstant extends Expression {
    private double value;

    public NumericConstant(double value) {
        this.value = value;
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public double getValue() {
        return value;
    }
}