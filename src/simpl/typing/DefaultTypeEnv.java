package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // Initialize with empty - predefined functions will be added via get()
    }

    @Override
    public Type get(Symbol x) {
        String name = x.toString();
        
         
        if (name.equals("fst")) {
            TypeVar t1 = new TypeVar(true);
            TypeVar t2 = new TypeVar(true);
            return new ArrowType(new PairType(t1, t2), t1);
        }
        if (name.equals("snd")) {
            TypeVar t1 = new TypeVar(true);
            TypeVar t2 = new TypeVar(true);
            return new ArrowType(new PairType(t1, t2), t2);
        }
        if (name.equals("hd")) {
            TypeVar t = new TypeVar(true);
            return new ArrowType(new ListType(t), t);
        }
        if (name.equals("tl")) {
            TypeVar t = new TypeVar(true);
            return new ArrowType(new ListType(t), new ListType(t));
        }
        
        // PCF (Programming Computable Functions) primitives
        if (name.equals("iszero")) {
            return new ArrowType(Type.INT, Type.BOOL);
        }
        if (name.equals("pred")) {
            return new ArrowType(Type.INT, Type.INT);
        }
        if (name.equals("succ")) {
            return new ArrowType(Type.INT, Type.INT);
        }
        
        
        return null;
    }

}
