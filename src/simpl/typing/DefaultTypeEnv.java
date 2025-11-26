package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
   
        TypeVar t1 = new TypeVar(true);
        TypeVar t2 = new TypeVar(true);
        TypeVar t = new TypeVar(true);

        // Build environment chain using anonymous subclasses
        TypeEnv env = new TypeEnv() {
            @Override
            public Type get(Symbol x) {
                if (x.toString().equals("fst")) 
                    return new ArrowType(new PairType(t1, t2), t1);
                if (x.toString().equals("snd"))
                    return new ArrowType(new PairType(t1, t2), t2);
                if (x.toString().equals("hd"))
                    return new ArrowType(new ListType(t), t);
                if (x.toString().equals("tl"))
                    return new ArrowType(new ListType(t), new ListType(t));
                return null;
            }
        };

        this.E = env;
        
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
