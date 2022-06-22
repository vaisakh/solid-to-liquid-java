package parser;

import expression.BinaryExpression;
import expression.Expression;
import expression.NumericConstant;
import expression.UnaryExpression;
import lexer.Lexer;
import lexer.Operator;
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

    public Expression callExpression() throws Exception {
        currentToken = getToken();
        return Expr();
    }

    //<Expr> ::= <Term> | Term { + | - } <Expr>
    public Expression Expr() throws Exception {
        Token lToken;
        Expression expression = Term();
        if(currentToken == Token.TOK_PLUS || currentToken == Token.TOK_SUB) {
            lToken = currentToken;
            currentToken = getToken();
            Expression expression2 = Expr();

            if(lToken == Token.TOK_PLUS) {
                expression = new BinaryExpression(expression, expression2, Operator.PLUS);
            } else {
                expression = new BinaryExpression(expression, expression2, Operator.MINUS);
            }
        }
        return expression;
    }

    //<Term> ::= <Factor> | <Factor> {*|/} <Term>
    public Expression Term() throws Exception {
        Token lToken;
        Expression expression = Factor();
        if(currentToken == Token.TOK_MUL || currentToken == Token.TOK_DIV) {
            lToken = currentToken;
            currentToken = getToken();
            Expression expression2 = Term();

            if(lToken == Token.TOK_MUL) {
                expression = new BinaryExpression(expression, expression2, Operator.MUL);
            } else {
                expression = new BinaryExpression(expression, expression2, Operator.DIV);
            }
        }
        return expression;
    }

    //<Factor>::= <number> | ( <expr> ) | {+|-} <factor>
    public Expression Factor() throws Exception {
        Token lToken;
        Expression expression = null;
        if(currentToken == Token.TOK_DOUBLE) {
            expression = new NumericConstant(getNumber());
            currentToken = getToken();
        } else if (currentToken == Token.TOK_OPAREN) {
            currentToken = getToken();
            expression = Expr(); // Recurse
            if(currentToken != Token.TOK_CPAREN) {
                System.out.println("Missing closing parenthesis.");
                throw new Exception();
            }
            currentToken = getToken();
        } else if(currentToken == Token.TOK_PLUS || currentToken == Token.TOK_SUB) {
            lToken = currentToken;
            currentToken = getToken();
            expression = Factor();

            if(lToken == Token.TOK_PLUS) {
                expression = new UnaryExpression(expression, Operator.PLUS);
            } else {
                expression = new UnaryExpression(expression, Operator.MINUS);
            }
        } else {
            System.out.println("Illegal Token.");
            throw new Exception();
        }
        return expression;
    }
}
