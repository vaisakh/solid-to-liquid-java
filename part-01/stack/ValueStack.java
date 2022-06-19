package stack;

/**
 * @author vaisakhvm
 */
public class ValueStack {
    double[] stack;
    int top = 0;

    public ValueStack() {
        stack = new double[256];
        top = 0;
    }

    public void clear() {
        top = 0;
    }

    public void push(double value) throws Exception {
        if(top == 255) {
            System.out.println("Stack Overflow.");
            throw new Exception();
        }
        stack[top++] = value;
    }

    public double pop() throws Exception {
        if(top == 0) {
            System.out.println("Stack Underflow.");
            throw new Exception();
        }
        return stack[--top];
    }
}
