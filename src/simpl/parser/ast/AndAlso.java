package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(tr1.s.compose(E));

        Substitution s = tr2.s.compose(tr1.s);
        s = s.compose(s.apply(tr1.t).unify(Type.BOOL));
        s = s.compose(s.apply(tr2.t).unify(Type.BOOL));

        return TypeResult.of(s, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);
        if (!(lv instanceof BoolValue)) {
            throw new RuntimeError("andalso: left operand not a boolean");
        }
        BoolValue left = (BoolValue) lv;

        if (!left.b) {
            return new BoolValue(false);
        }

        Value rv = r.eval(s);
        if (!(rv instanceof BoolValue)) {
            throw new RuntimeError("andalso: right operand not a boolean");
        }
        BoolValue right = (BoolValue) rv;

        return new BoolValue(left.b && right.b);
    }
}
