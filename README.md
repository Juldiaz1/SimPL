# SimPL Interpreter

A Java implementation of the SimPL (Simple Programming Language) interpreter for CSE3302/CSE5307 Course Project.

## Overview

SimPL is a simplified dialect of ML that supports both functional and imperative programming paradigms. This interpreter implements the complete SimPL language specification including:

- **Functional features**: First-class functions, recursion, let bindings
- **Imperative features**: References, assignment, while loops  
- **Type system**: Polymorphic type inference with let-polymorphism
- **Data structures**: Lists, pairs, references

## Features

- **Complete language support**: All SimPL expressions and types
- **Type inference**: Hindley-Milner type system with unification
- **Memory management**: Reference counting and garbage collection
- **Error handling**: Syntax, type, and runtime errors with meaningful messages
- **Predefined functions**: `fst`, `snd`, `hd`, `tl` for pair and list operations

## Project Structure
```
SimPL/
├── src/                    # Source code
│   └── simpl/
│       ├── interpreter/    # Runtime evaluation
│       ├── parser/         # Lexing and parsing
│       │   └── ast/        # Abstract syntax tree
│       └── typing/         # Type inference system
├── lib/                    # Dependencies (java-cup)
├── doc/
│   └── examples/           # Example SimPL programs
├── bin/                    # Compiled classes
└── SimPL.jar               # Runnable JAR
```


## Building

### Prerequisites
- Java JDK 11 or higher
- java-cup library (included in lib/)

### Compilation
```bash
# Compile all source files
javac -d bin -cp "lib/*" @sources.txt

# Create executable JAR
jar cfe SimPL.jar simpl.interpreter.Interpreter -C bin .

# Usage

## Running SimPL Programs

### Using the JAR file:
```bash
java -cp "SimPL.jar;lib/*" simpl.interpreter.Interpreter program.spl
```

### Using the provided batch file (Windows):
```bash
run.bat program.spl
```

### Examples:
```bash
run.bat doc/examples/plus.spl        # Output: 3
run.bat doc/examples/factorial.spl   # Output: 24
run.bat doc/examples/sum.spl         # Output: 6
```

## Language Examples

### Basic Arithmetic:
```spl
1 + 2 * 3
```

### Functions:
```spl
let add = fn x => fn y => x + y
in add 5 7
end
```

### Conditionals:
```spl
(if 5 > 3 then 100 else 200)
```

### Recursion:
```spl
let fact = rec f => fn x => 
  if x = 1 then 1 else x * (f (x - 1))
in fact 5
end
```

### Lists:
```spl
1 :: 2 :: 3 :: nil
```

### References:
```spl
let x = ref 5 in
x := 10;
!x
end
```

## Output Format

The interpreter outputs results according to the specification:
* Integers: Raw value (e.g., `42`)
* Booleans: `true` or `false`
* Functions: `fun`
* Lists: `list@length`
* Pairs: `pair@value1@value2`
* References: `ref@content`
* Unit: `unit`
* Errors: `syntax error`, `type error`, `runtime error`

## Testing

The project includes comprehensive test cases in `doc/examples/`:
* `plus.spl` - Basic function application
* `factorial.spl` - Recursive factorial
* `gcd1.spl`, `gcd2.spl` - Greatest common divisor
* `sum.spl` - List summation
* `map.spl` - Higher-order functions
* `pcf.*.spl` - Primitive computable function examples

## Implementation Details

* **Parser**: Generated using java-cup for LALR parsing
* **Type System**: Constraint-based typing with unification algorithm
* **Evaluation**: Environment-based interpreter with mutable state
* **Memory**: Heap-allocated references with pointer management

## Quick Start

1. Clone the repository:
```bash
git clone https://github.com/Juldiaz1/SimPL.git
cd SimPL
```

2. Run a SimPL program:
```bash
run.bat doc/examples/plus.spl
```

3. Create your own program:
```bash
echo "1 + 1" > test.spl
run.bat test.spl
```

## Troubleshooting

* **Class not found errors**: Ensure you're using `java -cp "SimPL.jar;lib/*"` or the `run.bat` file
* **Syntax errors**: Check SimPL syntax rules - conditionals need parentheses, proper keyword usage
* **File not found**: Use correct path to .spl files
