import java.util.ArrayList;

/**
 * Represents a single IR instruction. Instructions have an opcode {@link Kind},
 * a destination address, and zero, one, or two operand addresses.
 */
public class Instruction {
    enum Kind {
        MUL, DIV, MOD, ADD, SUB, POW,
        LT, LE, EQ, NE, OR,
        FLOOR, SQRT, LOG,
        I2F, S2I, S2F, I2S, F2S,
        STORE, PRINT, SCONCAT, READLINE, RANDOM
    }

    Kind kind;
    Address destination;
    ArrayList<Address> operands = new ArrayList<>();

    public Instruction(Kind kind, Address destination) {
        this.kind = kind;
        this.destination = destination;
        // Validate that caller is using the constructor correctly.
        switch(kind) {
            case RANDOM:
            case READLINE:
                assert destination != null;
                assert destination.kind == Address.Kind.TEMP;
                break;
            default:
                throw new IllegalArgumentException(kind.toString());
        }
    }

    public Instruction(Kind kind, Address destination, Address source)
    {
        this.kind = kind;
        this.destination = destination;
        this.operands.add(source);
        // Validate that caller is using the constructor correctly.
        switch(kind) {
            case F2S:  // Operand must be float
            case FLOOR:
            case SQRT:
                assert source.type == Type.FLOAT;
                assert destination.kind == Address.Kind.TEMP;
                break;
            case S2F:   // Operand must be string
            case S2I:
                assert source.type == Type.STRING;
                assert destination.kind == Address.Kind.TEMP;
                break;
            case I2F:   // Operand must be int
            case I2S:
                assert source.type == Type.INT;
                assert destination.kind == Address.Kind.TEMP;
                break;
            case PRINT:
                assert destination == null;
                break;
            case STORE:
                assert destination.kind == Address.Kind.VAR;
                break;
            default:
                throw new IllegalArgumentException(kind.toString());
        }
    }

    public Instruction(Kind kind, Address destination,
                       Address left, Address right)
    {
        this.kind = kind;
        this.destination = destination;
        this.operands.add(left);
        this.operands.add(right);
        // Validate that caller is using the constructor correctly.
        assert left.type == right.type;
        switch(kind) {
            case ADD: // numerics
            case DIV:
            case LE:
            case LT:
            case MUL:
            case POW:
            case SUB:
                assert left.type.isNumeric() || left.type == Type.BOOL;
                break;
            case SCONCAT: // strings
                assert left.type == Type.STRING;
                break;
            case OR:  // bools
                assert left.type == Type.BOOL;
                break;
            case MOD:  // ints
                assert left.type == Type.INT;
                break;
            case LOG:  // floats
                assert left.type == Type.FLOAT;
                break;
            case EQ:
            case NE:
                break;
            default:
                throw new IllegalArgumentException(kind.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(destination != null) {
            b.append(destination).append(" = ");
        }
        b.append(kind).append('(');
        boolean first = true;
        for(Address a : operands) {
            if(!first) b.append(", ");
            first = false;
            b.append(a);
        }
        b.append(')');
        return b.toString();
    }

    public static void main(String[] args) {
        // Just show some sample instructions
        Address a1 = new Address(Type.FLOAT);
        Address a2 = new Address(Type.FLOAT);
        Address a3 = new Address(Type.STRING);
        Address a4 = new Address(Type.STRING);

        Address pi   = new Address(Address.Kind.CONST, Type.FLOAT,  "3.14159");
        Address two  = new Address(Address.Kind.CONST, Type.INT,    "2");
        Address mesg = new Address(Address.Kind.CONST, Type.STRING, "PI^2 is ");

        BasicBlock block = new BasicBlock();
        block.add(new Instruction(Kind.I2F, a1, two));
        block.add(new Instruction(Kind.POW, a2, pi, a1));
        block.add(new Instruction(Kind.F2S, a3, a2));
        block.add(new Instruction(Kind.SCONCAT, a4, mesg, a3));
        block.add(new Instruction(Kind.PRINT, null, a4));

        System.out.println(block);

        InterpretIR interpreter = new InterpretIR();
        ArrayList<String> output = new ArrayList<>();
        interpreter.run(block, output);
        System.out.println(output);
    }
}
