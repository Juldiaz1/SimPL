package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.ArrowType;
import simpl.typing.ListType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class tl extends FunValue {

    public tl() {
        // TODO
         super(Env.empty, Symbol.symbol("l"), new Expr() {
            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("l"));
                if (!(v instanceof ConsValue)) {
                    throw new RuntimeError("tl expects a non-empty list");
                }
                ConsValue list = (ConsValue) v;
                return list.v2; // tail (rest of the list)
            }

            @Override
            public TypeResult typecheck(TypeEnv e) throws TypeError {
                // tl : t list -> t list
                TypeVar t = new TypeVar(true);
                return TypeResult.of(new ArrowType(new ListType(t), new ListType(t)));
            }
        });
    }
}
