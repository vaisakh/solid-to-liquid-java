package parser;

import lexer.Lexer;
import lexer.Token;
import stack.ValueStack;

/**
 * @author vaisakhvm
 */

public class RDParser extends Lexer {
    Token currentToken;
    ValueStack valueStack = new ValueStack();

    public RDParser(String expression) {
        super(expression);
    }

    public double callExpression() throws Exception {
        valueStack.clear();
        currentToken = getToken();
        Expr();
        double value = valueStack.pop();
        return value;
    }

    //<Expr> ::= <Term> | Term { + | - } <Expr>
    public void Expr() throws Exception {
        Token lToken;
        Term();
        if(currentToken == Token.TOK_PLUS || currentToken == Token.TOK_SUB) {
            lToken = currentToken;
            currentToken = getToken();
            Expr();
            double x = valueStack.pop();
            double y = valueStack.pop();
            valueStack.push((lToken == Token.TOK_PLUS) ? (x + y) : (x - y));
        }
    }

    //<Term> ::= <Factor> | <Factor> {*|/} <Term>
    public void Term() throws Exception {
        Token lToken;
        Factor();
        if(currentToken == Token.TOK_MUL || currentToken == Token.TOK_DIV) {
            lToken = currentToken;
            currentToken = getToken();
            Term();
            double x = valueStack.pop();
            double y = valueStack.pop();
            if(x == 0) {
                System.out.println("Division By Zero Error");
                throw new Exception();
            }
            valueStack.push((lToken == Token.TOK_MUL) ? (x * y) : (x / y));

        }
    }

    //<Factor>::= <number> | ( <expr> ) | {+|-} <factor>
    public void Factor() throws Exception {
        Token lToken;
        if(currentToken == Token.TOK_DOUBLE) {
            valueStack.push(getNumber());
            currentToken = getToken();
        } else if (currentToken == Token.TOK_OPAREN) {
            currentToken = getToken();
            Expr(); // Recurse
            if(currentToken != Token.TOK_CPAREN) {
                System.out.println("Missing closing parenthesis.");
                throw new Exception();
            }
            currentToken = getToken();
        } else if(currentToken == Token.TOK_PLUS || currentToken == Token.TOK_SUB) {
            lToken = currentToken;
            currentToken = getToken();
            Factor();
            double x = valueStack.pop();
            if(lToken == Token.TOK_SUB) {
                x = -x;
            }
            valueStack.push(x);
        } else {
            System.out.println("Illegal Token.");
            throw new Exception();
        }
    }
}
