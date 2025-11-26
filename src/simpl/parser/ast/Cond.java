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

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = e1.typecheck(E);
        TypeResult tr2 = e2.typecheck(E);
        TypeResult tr3 = e3.typecheck(E);

        Substitution s = tr3.s.compose(tr2.s).compose(tr1.s);
        s = s.compose(tr1.t.unify(Type.BOOL));
        s = s.compose(tr2.t.unify(tr3.t));

        return TypeResult.of(s, s.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = e1.eval(s);

        if (!(v1 instanceof BoolValue)) {
            throw new RuntimeError("Condition must evaluate to a boolean");
        }

        BoolValue cond = (BoolValue) v1;
        if (cond.b) {
            return e2.eval(s);
        } else {
            return e3.eval(s);
        }
    }
}
