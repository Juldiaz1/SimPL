package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
       TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);  // no E.apply(tr1.s) — just reuse E

        // Both operands must be integers
        Substitution s = tr1.s
            .compose(tr2.s)
            .compose(tr1.t.unify(Type.INT))
            .compose(tr2.t.unify(Type.INT));

        // The result of any relational expression is boolean
        return TypeResult.of(s, Type.BOOL);
    }
}
