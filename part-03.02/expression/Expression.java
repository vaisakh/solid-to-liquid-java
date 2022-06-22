package expression;

import visitor.Visitor;

/**
 * @author vaisakhvm
 */
public abstract class Expression {
    public abstract double accept(Visitor visitor) throws Exception;
}