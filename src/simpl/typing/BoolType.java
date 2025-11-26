package simpl.typing;

final class BoolType extends Type {

    protected BoolType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        if (!(t instanceof BoolType)) {
            throw new TypeMismatchError();
        }
        return Substitution.IDENTITY;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return this;
    }

    public String toString() {
        return "bool";
    }
}
