package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        Substitution s1 = tr1.s.compose(tr1.t.unify(Type.BOOL));

        TypeResult tr2 = r.typecheck(s1.apply(E));
        Substitution s2 = tr2.s.compose(tr2.t.unify(Type.BOOL));

        Substitution s = s2.compose(s1);
        return TypeResult.of(s, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);

        if (!(lv instanceof BoolValue)) {
            throw new RuntimeError("Left operand of 'orelse' must be boolean");
        }

        
        if (((BoolValue) lv).b) {
            return new BoolValue(true);
        }

        Value rv = r.eval(s);

        if (!(rv instanceof BoolValue)) {
            throw new RuntimeError("Right operand of 'orelse' must be boolean");
        }

        return new BoolValue(((BoolValue) rv).b);
    }
}
