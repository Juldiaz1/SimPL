package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution s = tr2.s.compose(tr1.s);
        s = s.compose(tr1.t.unify(tr2.t));

        Type t = s.apply(tr1.t);

        // Ensure t is an equality type
        if (!t.isEqualityType()) {
            throw new TypeError("Operands of '=' must be equality types");
        }

        return TypeResult.of(s, Type.BOOL);
    }
}
