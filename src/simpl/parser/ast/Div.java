package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);
        Value rv = r.eval(s);

        if (!(lv instanceof IntValue) || !(rv instanceof IntValue)) {
            throw new RuntimeError("Division operands must be integers");
        }

        int left = ((IntValue) lv).n;
        int right = ((IntValue) rv).n;

        if (right == 0) {
            throw new RuntimeError("Division by zero");
        }

        return new IntValue(left / right);
    }
}
