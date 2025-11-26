package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution s = tr2.s.compose(tr1.s);
        TypeVar t = new TypeVar(true);

        s = s.compose(tr1.t.unify(t));
        s = s.compose(tr2.t.unify(new ListType(t)));

        return TypeResult.of(s, new ListType(s.apply(t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1, v2);
    }
}
