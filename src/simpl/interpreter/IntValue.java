package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (this == other) {
            return true;
        }
        if (!(other instanceof IntValue)) {
            return false;
        }
        IntValue o = (IntValue) other;
        return this.n == o.n;

    }
}
