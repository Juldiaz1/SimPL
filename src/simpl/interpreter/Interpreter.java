package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Interpreter {

    public void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            
            TypeResult typeResult = program.typecheck(new DefaultTypeEnv());
            if (typeResult != null) {
                System.out.println(typeResult.t);
            } else {
                System.out.println("type check returned null");
            }
            
            System.out.println(program.eval(new InitialState()));
        }
        catch (SyntaxError e) {
            System.out.println("syntax error");
        }
        catch (TypeError e) {
            System.out.println("type error");
        }
        catch (RuntimeError e) {
            System.out.println("runtime error");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
        interpret(args[0]);
   }   
        interpret("doc/examples/pcf.fibonacci.spl");
    }
}
