package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);
        TypeVar t = new TypeVar(true);

        Substitution s = tr.s.compose(tr.s.apply(tr.t).unify(new RefType(t)));

        return TypeResult.of(s, s.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = e.eval(s);

        if (!(v instanceof RefValue)) {
            throw new RuntimeError("Attempting to dereference a non-reference value");
        }

        RefValue ref = (RefValue) v;
        return s.M.get(ref.p);
    }
}
