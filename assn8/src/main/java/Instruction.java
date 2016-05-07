import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Represents a single IR instruction. Instructions have an opcode {@link Kind},
 * a destination address, and zero, one, or two operand addresses.
 */
public class Instruction implements Iterable<Address> {
    enum Kind {
        MUL, DIV, MOD, ADD, SUB, POW,
        LT, LE, EQ, NE, OR,
        FLOOR, SQRT, LOG,
        I2F, S2I, S2F, I2S, F2S,
        STORE, PRINT, SCONCAT, READLINE, RANDOM,
        JUMP, BRANCH, PHI, END
    }

    final Kind kind;
    private final ArrayList<Address> operands = new ArrayList<>();

    Address dest() {
        return operands.get(0);
    }

    Address first() {
        return operands.get(1);
    }

    Address second() {
        return operands.get(2);
    }

    @Override
    public Iterator<Address> iterator() {
        return operands.iterator();
    }

    Instruction(Kind kind, Address... addrs) {
        this.kind = kind;
        Collections.addAll(operands, addrs);
        // Validate that caller is using the constructor correctly.
        switch(kind) {
            case END:
                assert operands.size() == 0;
                break;
            case RANDOM:    // Only a destination operand
            case READLINE:
                assert operands.size() == 1;
                assert dest().kind == Address.Kind.TEMP;
                break;
            case F2S:       // One operand, must be float
            case FLOOR:
            case SQRT:
                assert operands.size() == 2;
                assert dest().kind == Address.Kind.TEMP;
                assert first().type == Type.FLOAT;
                break;
            case S2F:       // One operand, must be string
            case S2I:
                assert operands.size() == 2;
                assert dest().kind == Address.Kind.TEMP;
                assert first().type == Type.STRING;
                break;
            case I2F:       // One operand, must be int
            case I2S:
                assert operands.size() == 2;
                assert dest().kind == Address.Kind.TEMP;
                assert first().type == Type.INT;
                break;
            case PRINT:     // No destination, one operand
                assert operands.size() == 1;
                break;
            case STORE:     // VAR destination, one operand
                assert operands.size() == 2;
                assert dest().kind == Address.Kind.VAR;
                break;
            case ADD:       // Two numeric operands
            case DIV:
            case MUL:
            case POW:
            case SUB:
                assert operands.size() == 3;
                assert first().type.isNumericOrBool();
                assert second().type.isNumericOrBool();
                assert dest().type.isNumericOrBool();
                break;
            case SCONCAT:   // Two string operands, and dest
                assert operands.size() == 3;
                assert dest().type == Type.STRING;
                assert first().type == Type.STRING;
                assert second().type == Type.STRING;
                break;
            case OR:        // Two boolean operands, and dest
                assert operands.size() == 3;
                assert dest().type == Type.BOOL;
                assert first().type == Type.BOOL;
                assert second().type == Type.BOOL;
                break;
            case MOD:       // Two ints operands, and dest
                assert operands.size() == 3;
                assert dest().type == Type.INT;
                assert first().type == Type.INT;
                assert second().type == Type.INT;
                break;
            case LOG:       // Two floats operands, and dest
                assert operands.size() == 3;
                assert dest().type   == Type.FLOAT;
                assert first().type  == Type.FLOAT;
                assert second().type == Type.FLOAT;
                break;
            case LE:
            case LT:        // Two numeric operands, bool dest
                assert operands.size() == 3;
                assert dest().type == Type.BOOL;
                assert first().type.isNumeric();
                assert second().type.isNumeric();
                break;
            case EQ:
            case NE:        // Two operands of same type
                assert operands.size() == 3;
                assert dest().type == Type.BOOL;
                assert first().type == second().type;
                break;
            case BRANCH:    // One boolean operand, two labels
                assert operands.size() == 3;
                assert dest().type == Type.BOOL;
                assert first().kind == Address.Kind.LABEL;
                assert second().kind == Address.Kind.LABEL;
                break;
            case JUMP:      // One label
                assert operands.size() == 1;
                assert dest().kind == Address.Kind.LABEL;
                break;
            case PHI:
                assert operands.size() == 5;
                assert operands.get(0).type == operands.get(1).type;
                assert operands.get(1).type == operands.get(3).type;
                assert operands.get(2).kind == Address.Kind.LABEL;
                assert operands.get(4).kind == Address.Kind.LABEL;
                break;
            default:
                throw new IllegalArgumentException(kind.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        int i = 0;
        switch(kind) {
            case PRINT:  // These do not have destination operands
            case JUMP:
            case BRANCH:
            case END:
                break;
            default:
                b.append(operands.get(i++)).append(" = ");
        }
        b.append(kind).append('(');
        boolean first = true;
        for( ; i < operands.size(); i++) {
            if(!first) b.append(", ");
            first = false;
            b.append(operands.get(i));
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

        IRGraph ir = new IRGraph();
        ir.add(new Instruction(Kind.I2F, a1, two));
        ir.add(new Instruction(Kind.POW, a2, pi, a1));
        ir.add(new Instruction(Kind.F2S, a3, a2));
        ir.add(new Instruction(Kind.SCONCAT, a4, mesg, a3));
        ir.add(new Instruction(Kind.PRINT, a4));

        System.out.println(ir);

        ArrayList<String> output = new ArrayList<>();
        new InterpretIR(ir, output);
        System.out.println(output);
    }
}
