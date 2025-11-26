package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Sub extends ArithExpr {

    public Sub(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " - " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
         Value v1 = l.eval(s);
        Value v2 = r.eval(s);

        // Both must be integers
        if (!(v1 instanceof IntValue) || !(v2 instanceof IntValue)) {
            throw new RuntimeError("Subtraction requires integer operands");
        }

        int n1 = ((IntValue) v1).n;
        int n2 = ((IntValue) v2).n;

        // Return the result as an IntValue
        return new IntValue(n1 - n2);
    }
}
