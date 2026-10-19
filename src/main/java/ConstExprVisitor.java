import framework.project2.ConstExprResult;
import generated.SplcBaseVisitor;
import generated.SplcParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConstExprVisitor extends SplcBaseVisitor<Integer> {

    private final List<ConstExprResult> results = new ArrayList<>();

    public final List<ConstExprResult> getResults() {
        return Collections.unmodifiableList(results);
    }

    protected final void addResult(String expression, int value) {
        results.add(new ConstExprResult(expression, value));
    }

    /*
     * TODO:
     *
     * 1. Find the complete expression on the right-hand side of each local
     *    variable definition.
     * 2. Determine whether that complete expression is a constexpr according
     *    to the Project 2 specification.
     * 3. Evaluate it if it is a constexpr.
     * 4. Store the result with addResult(...).
     *
     * Do not add constexpr subexpressions of a non-constexpr whole expression.
     */

    @Override
    public Integer visitLocalVarDef(SplcParser.LocalVarDefContext ctx) {

        
        SplcParser.ExpressionContext expression = ctx.expression();
        if (expression != null && isConstExpr(expression)) {
            addResult(expression.getText(), getValue(expression));
        }

        return null;
    }

    public boolean isConstExpr(SplcParser.ExpressionContext ctx){
            
            if(ctx.getChildCount() == 1) {
                if(ctx.Number() != null) {
                    return true;
                }
            }else if(ctx.getChild(0).getText().equals("(") && ctx.getChild(2).getText().equals(")")) {
                if(isConstExpr((SplcParser.ExpressionContext) ctx.getChild(1))) {
                    return true;
                }
            }else if(ctx.getChild(0).getText().equals("-") || ctx.getChild(0).getText().equals("+") ) {
                if(isConstExpr((SplcParser.ExpressionContext) ctx.getChild(1))) {
                    return true;
                }
            }else if(ctx.getChild(1).getText().equals("+") || ctx.getChild(1).getText().equals("-") || ctx.getChild(1).getText().equals("*") || ctx.getChild(1).getText().equals("/")|| ctx.getChild(1).getText().equals("%")) {
                if(isConstExpr((SplcParser.ExpressionContext) ctx.getChild(0))
                    && isConstExpr((SplcParser.ExpressionContext) ctx.getChild(2))) {
                    return true;
                }
            }

        return false;
    }

    public int getValue(SplcParser.ExpressionContext ctx) {

            if(ctx.getChildCount() == 1) {
                if(ctx.Number() != null) {
                    return Integer.parseInt(ctx.getText());
                }
            }else if(ctx.getChild(0).getText().equals("(") && ctx.getChild(2).getText().equals(")")) {
                return getValue((SplcParser.ExpressionContext) ctx.getChild(1));
            }else if(ctx.getChild(0).getText().equals("-") || ctx.getChild(0).getText().equals("+") ) {
                if(ctx.getChild(0).getText().equals("-")) {
                    return -1 * getValue((SplcParser.ExpressionContext) ctx.getChild(1));
                }else {
                    return getValue((SplcParser.ExpressionContext) ctx.getChild(1));
                }
            }else if(ctx.getChild(1).getText().equals("+") || ctx.getChild(1).getText().equals("-") || ctx.getChild(1).getText().equals("*") || ctx.getChild(1).getText().equals("/")|| ctx.getChild(1).getText().equals("%")) {
                if(ctx.getChild(1).getText().equals("+")){
                        return getValue((SplcParser.ExpressionContext) ctx.getChild(0))
                            + getValue((SplcParser.ExpressionContext) ctx.getChild(2));
                }else if(ctx.getChild(1).getText().equals("-")){
                        return getValue((SplcParser.ExpressionContext) ctx.getChild(0))
                            - getValue((SplcParser.ExpressionContext) ctx.getChild(2));
                }else if(ctx.getChild(1).getText().equals("*")){
                        return getValue((SplcParser.ExpressionContext) ctx.getChild(0))
                            * getValue((SplcParser.ExpressionContext) ctx.getChild(2));
                }else if(ctx.getChild(1).getText().equals("/")){    
                        return getValue((SplcParser.ExpressionContext) ctx.getChild(0))
                            / getValue((SplcParser.ExpressionContext) ctx.getChild(2));
                }else if(ctx.getChild(1).getText().equals("%")){
                        return getValue((SplcParser.ExpressionContext) ctx.getChild(0))
                            % getValue((SplcParser.ExpressionContext) ctx.getChild(2));
                }      
            }
  
        return 0;
    }

}
