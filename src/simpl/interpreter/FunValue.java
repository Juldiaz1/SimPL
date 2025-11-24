package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class FunValue extends Value {

    public final Env E;
    public final Symbol x;
    public final Expr e;

    public FunValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
if (this == other) {
            return true;
        }
        // In SimPL, two function values are never considered equal
        if (!(other instanceof FunValue)) {
            return false;
        }
        return false;
    }
}
