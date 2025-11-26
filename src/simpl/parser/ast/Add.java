package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Add extends ArithExpr {

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " + " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);
        Value rv = r.eval(s);

        
        if (!(lv instanceof IntValue) || !(rv instanceof IntValue)) {
            throw new RuntimeError("Addition expects integer operands");
        }

        int sum = ((IntValue) lv).n + ((IntValue) rv).n;
        return new IntValue(sum);
    }
}
