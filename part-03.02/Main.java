import expression.Expression;
import parser.RDParser;
import visitor.StackEvaluatorVisitor;

/**
 * @author vaisakhvm
 */

public class Main {
    public static void main(String args[]) {
        if(args.length == 0 || args.length > 1 ) {
            System.out.println("Usage : Expr \"<expr>\" \n");
            System.out.println(" eg:- Expr \"2*3+4\" \n");
            System.out.println(" Expr \"-2-3\" \n");
            return;
        }

        try {
            RDParser parser = new RDParser(args[0]);
            Expression expression = parser.callExpression();
            System.out.println("Evaluated value: " + expression);

            StackEvaluatorVisitor s = new StackEvaluatorVisitor();
            expression.accept(s);
            System.out.println(s.getValue());

        } catch (Exception e) {
            System.out.println("Error evaluating expression\n");
            System.out.println(e);
            return;
        }
    }
}