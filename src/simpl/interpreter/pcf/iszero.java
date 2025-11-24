package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
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

public class iszero extends FunValue {

    public iszero() {
        // TODO
        super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("x"));
                if (!(v instanceof IntValue)) {
                    throw new RuntimeError("iszero expects an integer");
                }
                IntValue iv = (IntValue) v;
                return new BoolValue(iv.n == 0);
            }

            @Override
            public TypeResult typecheck(TypeEnv e) throws TypeError {
                // iszero : int -> bool
                return simpl.typing.TypeResult.of(new ArrowType(simpl.typing.Type.INT, simpl.typing.Type.BOOL));

            }
        });
    }
}
