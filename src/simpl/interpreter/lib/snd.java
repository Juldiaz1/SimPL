package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.ArrowType;
import simpl.typing.PairType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class snd extends FunValue {

    public snd() {
        // TODO
         super(Env.empty, Symbol.symbol("p"), new Expr() {
            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("p"));
                if (!(v instanceof PairValue)) {
                    throw new RuntimeError("snd expects a pair");
                }
                PairValue pair = (PairValue) v;
                return pair.v2;
            }

            @Override
            public TypeResult typecheck(TypeEnv e) throws TypeError {
                // snd : t1 × t2 -> t2
                TypeVar t1 = new TypeVar(true);
                TypeVar t2 = new TypeVar(true);
                return TypeResult.of(new ArrowType(new PairType(t1, t2), t2));
            }
        });
    }
}
