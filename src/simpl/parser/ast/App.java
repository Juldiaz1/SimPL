package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);
        TypeVar t = new TypeVar(true);

        Substitution s3 = tr2.s.compose(tr1.s);
        Substitution s4 = tr1.t.unify(new ArrowType(tr2.t, t));

        Substitution s = s4.compose(s3);

        return TypeResult.of(s, s.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);
        Value rv = r.eval(s);

        if (lv instanceof FunValue) {
            FunValue f = (FunValue) lv;
            Env newEnv = new Env(f.E, f.x, rv);
            return f.e.eval(State.of(newEnv, s.M, s.p));
        }

        if (lv instanceof RecValue) {
            RecValue recVal = (RecValue) lv;
            // The rec value already has itself bound in its environment
            // Just bind the argument
            Env newEnv = new Env(recVal.E, recVal.x, rv);
            return recVal.e.eval(State.of(newEnv, s.M, s.p));
        }

        throw new RuntimeException("Application expects a function");
    }
}
