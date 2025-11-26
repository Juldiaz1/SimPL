package simpl.interpreter;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public String toString() {
        return "ref@" + p;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (this == other) {
            return true;
        }
        if (!(other instanceof RefValue)) {
            return false;
        }
        RefValue o = (RefValue) other;
        
        return this.p == o.p;
    }
}
