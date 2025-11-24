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

public class hd extends FunValue {

    public hd() {
        // TODO
        super(Env.empty, Symbol.symbol("l"), new Expr() {
            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("l"));
                if (!(v instanceof ConsValue)) {
                    throw new RuntimeError("hd expects a non-empty list");
                }
                ConsValue list = (ConsValue) v;
                return list.v1; // head element
            }

            @Override
            public TypeResult typecheck(TypeEnv e) throws TypeError {
                // hd : t list -> t
                TypeVar t = new TypeVar(true);
                return TypeResult.of(new ArrowType(new ListType(t), t));
            }
        });
    }
}
