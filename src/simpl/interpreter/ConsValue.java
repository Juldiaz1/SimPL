package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        // TODO
        int length = 1;
        Value current = v2;
        while (current instanceof ConsValue) {
            length++;
            current = ((ConsValue) current).v2;
        }
        return "list@" + length;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConsValue)) {
            return false;
        }
        ConsValue o = (ConsValue) other;
        return v1.equals(o.v1) && v2.equals(o.v2);
    }

}
