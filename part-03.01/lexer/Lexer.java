package lexer;

/**
 * @author vaisakhvm
 */
public class Lexer {
    private String expression;
    int length;
    int cursor;
    double number;

    public Lexer(String expression) {
        this.expression = expression;
        this.length = expression.length();
        this.cursor = 0;
    }

    public Token getToken() throws Exception {
        Token token = Token.ILLEGAL_TOKEN;

        // Skip white spaces
        while(cursor < length && (expression.charAt(cursor) == ' ' || expression.charAt(cursor) == '\t')) {
            cursor++;
        }

        //Return null if end of string
        if(cursor == length) {
            return Token.TOK_NULL;
        }

        switch (expression.charAt(cursor)) {
            case '+':
                token = Token.TOK_PLUS;
                cursor++;
                break;
            case '-':
                token = Token.TOK_SUB;
                cursor++;
                break;
            case '/':
                token = Token.TOK_DIV;
                cursor++;
                break;
            case '*':
                token = Token.TOK_MUL;
                cursor++;
                break;
            case '(':
                token = Token.TOK_OPAREN;
                cursor++;
                break;
            case ')':
                token = Token.TOK_CPAREN;
                cursor++;
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            {
                String str = "";
                while (cursor < length &&
                        (expression.charAt(cursor) == '0' ||
                                expression.charAt(cursor) == '1' ||
                                expression.charAt(cursor) == '2' ||
                                expression.charAt(cursor) == '3' ||
                                expression.charAt(cursor) == '4' ||
                                expression.charAt(cursor) == '5' ||
                                expression.charAt(cursor) == '6' ||
                                expression.charAt(cursor) == '7' ||
                                expression.charAt(cursor) == '8' ||
                                expression.charAt(cursor) == '9'))
                {
                    str += String.valueOf(expression.charAt(cursor));
                    cursor++;
                }
                number = Double.valueOf(str);
                token = Token.TOK_DOUBLE;
            }
            break;
            default: {
                System.out.println("Error While Analyzing Tokens");
                throw new Exception();
            }

        }

        return token;
    }

    public double getNumber() {
        return this.number;
    }
}