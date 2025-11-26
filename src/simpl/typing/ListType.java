package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof ListType) {
            ListType listOther = (ListType) t;
            return t.unify(listOther.t);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return new ListType(t.replace(a, t));
    }

    public String toString() {
        return t + " list";
    }
}
