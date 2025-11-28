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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = e1.typecheck(E);
        TypeResult tr2 = e2.typecheck(tr1.s.compose(E));

        Substitution s = tr2.s.compose(tr1.s);
        s = s.compose(s.apply(tr1.t).unify(Type.BOOL));
        s = s.compose(s.apply(tr2.t).unify(Type.UNIT));

        return TypeResult.of(s, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        while (true) {
            Value cond = e1.eval(s);

            if (!(cond instanceof BoolValue)) {
                throw new RuntimeError("Condition in while loop must be boolean");
            }

            if (!((BoolValue) cond).b) {
                break; // exit loop
            }

            e2.eval(s);
        }

        return Value.UNIT;
    }
}
