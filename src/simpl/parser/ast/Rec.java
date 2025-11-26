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
        RecValue v = new RecValue(s.E, x, e);
    
        Env newEnv = new Env(s.E, x, v);
    
        return e.eval(State.of(newEnv, s.M, s.p));
    }
}
