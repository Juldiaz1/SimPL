package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (this == other) {
            return true;
        }
        if (!(other instanceof BoolValue)) {
            return false;
        }
        BoolValue o = (BoolValue) other;
        return this.b == o.b;
    }
}
