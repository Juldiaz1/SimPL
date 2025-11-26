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

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution s = tr2.s.compose(tr1.s);

        TypeVar t = new simpl.typing.TypeVar(true);
        s = s.compose(tr1.t.unify(new RefType(t)));
        s = s.compose(tr2.t.unify(t));

        return TypeResult.of(s, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);
        Value rv = r.eval(s);

        if (!(lv instanceof RefValue)) {
            throw new RuntimeError("Assignment requires a reference on the left-hand side");
        }

        RefValue ref = (RefValue) lv;
        s.M.put(ref.p, rv); 
        return simpl.interpreter.Value.UNIT; 
    }
}
