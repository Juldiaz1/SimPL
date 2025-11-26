package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        if (!(t instanceof ArrowType)) {
            throw new TypeMismatchError();
        }

        ArrowType at = (ArrowType) t;
        Substitution s1 = t1.unify(at.t1);
        Substitution s2 = t2.unify(at.t2);

        return s2.compose(s1);
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return new ArrowType(t1.replace(a, t), t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
