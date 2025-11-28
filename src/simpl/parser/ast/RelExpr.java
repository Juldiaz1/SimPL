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
        TypeResult tr2 = r.typecheck(tr1.s.compose(E));

        Substitution s = tr2.s.compose(tr1.s);
        s = s.compose(s.apply(tr1.t).unify(Type.INT));
        s = s.compose(s.apply(tr2.t).unify(Type.INT));

        return TypeResult.of(s, Type.BOOL);
    }
}
