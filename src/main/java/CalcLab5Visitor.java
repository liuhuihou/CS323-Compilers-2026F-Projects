import generated.Calc1.Calc1BaseVisitor;
import generated.Calc1.Calc1Parser;

public class CalcLab5Visitor extends Calc1BaseVisitor<Double> {



    @Override 
    public Double visitAren(Calc1Parser.ArenContext ctx) {
        return visit(ctx.expr());
    }


    @Override
    public Double visitMulDiv( Calc1Parser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.MUL() != null) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override 
    public Double visitAddSub(Calc1Parser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.ADD() != null) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Double visitFactor(Calc1Parser.FactorContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }







}