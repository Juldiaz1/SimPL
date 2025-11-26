package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        Type t = E.get(x);
        if (t == null) {
            throw new TypeError("Unbound variable: " + x);
        }
        return TypeResult.of(t);
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
            return new FunValue(new Env(rv.E, rv.x, rv), rv.x, rv.e);
        }

    
        return v;
        
    }
}
