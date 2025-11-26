package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar t = new TypeVar(true);

        TypeResult tr = e.typecheck(TypeEnv.of(E, x, t));

        Substitution s = tr.s.compose(t.unify(tr.t));

        return TypeResult.of(s, s.apply(tr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = s.E.get(x);
        if (v == null) {
            throw new RuntimeError("Unbound variable: " + x);
        }

        
        if (v instanceof RecValue) {
            RecValue rv = (RecValue) v;
            
            Env recEnv = new Env(rv.E, rv.x, rv);
            return rv.e.eval(State.of(recEnv, s.M, s.p));
        }

        return v;
    }
}
