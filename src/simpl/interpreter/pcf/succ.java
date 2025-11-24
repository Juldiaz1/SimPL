package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.ArrowType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class succ extends FunValue {

    public succ() {
        // TODO
        super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("x"));
                if (!(v instanceof IntValue)) {
                    throw new RuntimeError("succ expects an integer");
                }
                IntValue iv = (IntValue) v;
                return new IntValue(iv.n + 1);
            }

            @Override
            public TypeResult typecheck(TypeEnv e) throws TypeError {
                // succ : int -> int
                return simpl.typing.TypeResult.of(new ArrowType(simpl.typing.Type.INT, simpl.typing.Type.INT));

            }
        });
    }
}
