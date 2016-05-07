import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Runs an IR program by interpreting each instruction. This is useful for
 * validating translations in the absence of a back end to compile the IR
 * to native code.
 */
class InterpretIR {

    private final HashMap<Object, Value> values = new HashMap<>();
    private final ArrayList<String> out;
    private Integer prevLabel, currLabel;

    InterpretIR(IRGraph graph, ArrayList<String> out) {
        this.out = out;
        this.currLabel = graph.startLabel();
        while(currLabel != null) {
            run(graph.getBlock(currLabel));
        }
    }

    private void saveValue(Address a, Value v) {
        switch(a.kind) {
            case TEMP:
                values.put(a.serialNum, v);
                break;
            case VAR:
                values.put(a.value, v);
                break;
            default:
                assert false;
        }
    }

    private Value loadValue(Address a) {
        switch(a.kind) {
            case TEMP:
                return values.get(a.serialNum);
            case VAR:
                return values.get(a.value);
            case CONST:
                return Value.fromConst(a);
            default:
                throw new UnsupportedOperationException(a.kind.toString());
        }
    }

    /** Run a basic block.
     */
    private void run(BasicBlock block) {
        for(Instruction i : block) {
            run(i);
        }
    }

    /**
     * Run a single instruction.
     */
    private void run(Instruction instr) {
        switch (instr.kind) {
            case END:
                currLabel = null;
                break;
            case STORE: {
                Address a = instr.first();
                saveValue(instr.dest(), loadValue(a));
                break;
            }
            case PRINT: {
                Address a = instr.dest();
                String s = loadValue(a).toString();
                if(a.type == Type.BOOL) {
                    s = s.equals("0")? "False" : "True";
                }
                out.add(s);
                break;
            }
            case BRANCH: {
                Address a = instr.dest();
                String s = loadValue(a).toString();
                Address target = s.equals("0")? instr.first() : instr.second();
                jump(target);
                break;
            }
            case JUMP: {
                Address target = instr.dest();
                jump(target);
                break;
            }
            case I2F:
            case FLOOR:
            case SQRT: {
                Value v = loadValue(instr.first());
                Value r = v.unary(instr.kind);
                saveValue(instr.dest(), r);
                break;
            }
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
            case POW:
            case LOG:
            case LT:
            case LE:
            case EQ:
            case NE:
            case OR:
            case SCONCAT: {
                Value left = loadValue(instr.first());
                Value right = loadValue(instr.second());
                Value result = left.operator(instr.kind, right);
                saveValue(instr.dest(), result);
                break;
            }
            case F2S: {
                DoubleValue v = (DoubleValue)loadValue(instr.first());
                saveValue(instr.dest(), new StringValue(v.toString()));
                break;
            }
            case PHI: {
                Address result = null;
                Address value = null;
                for(Address a : instr) {
                    if(result == null) {
                        result = a;
                    }
                    else if(a.kind == Address.Kind.LABEL && a.serialNum == prevLabel) {
                        saveValue(result,loadValue(value));
                        break;
                    }
                    else {
                        value = a;
                    }
                }
                break;
            }

            default:
                throw new UnsupportedOperationException(instr.kind.toString());
        }
    }

    private void jump(Address target) {
        prevLabel = currLabel;
        currLabel = target.serialNum;
    }

    private static abstract class Value {
        abstract Value operator(Instruction.Kind k, Value v);
        abstract Value unary(Instruction.Kind k);
        static Value fromConst(Address a) {
            assert a.kind == Address.Kind.CONST;
            switch(a.type) {
                case FLOAT:
                    return new DoubleValue(a.value);
                case INT:case BOOL:
                    return new IntegerValue(a.value);
                case STRING:
                    return new StringValue(a.value);
                default:
                    throw new UnsupportedOperationException(a.type.toString());
            }
        }
    }

    private static class StringValue extends Value {
        private final String s;
        StringValue(String s) {
            this.s = s;
        }
        @Override
        public String toString() {
            return s;
        }
        @Override
        Value operator(Instruction.Kind k, Value v) {
            switch (k) {
                case SCONCAT:
                    return new StringValue(s + v);
                default:
                    throw new UnsupportedOperationException(k.toString());
            }
        }
        @Override
        Value unary(Instruction.Kind k) {
            throw new UnsupportedOperationException(k.toString());
        }
    }

    private static class DoubleValue extends Value {
        private final double d;
        DoubleValue(double d) {
            this.d = d;
        }
        DoubleValue(String s) {
            d = Double.parseDouble(s);
        }
        @Override
        public String toString() {
            return String.format("%.6e", d);
        }
        @Override
        Value unary(Instruction.Kind k) {
            switch(k) {
                case FLOOR:
                    return new IntegerValue(String.format("%.0f", Math.floor(d)));
                case SQRT:
                    return new DoubleValue(Math.sqrt(d));
                default:
                    throw new UnsupportedOperationException(k.toString());
            }
        }
        @Override
        Value operator(Instruction.Kind k, Value v) {
            double r;
            DoubleValue that = (DoubleValue) v;
            switch(k) {
                case ADD: r = this.d + that.d; break;
                case SUB: r = this.d - that.d; break;
                case MUL: r = this.d * that.d; break;
                case DIV: r = this.d / that.d; break;
                case POW: r = Math.pow(this.d, that.d); break;
                case LOG: r = Math.log(this.d) / Math.log(that.d); break;
                case LT: return new IntegerValue(this.d < that.d);
                case LE: return new IntegerValue(this.d <= that.d);
                case EQ: return new IntegerValue(this.d == that.d);
                case NE: return new IntegerValue(this.d != that.d);
                default:
                    throw new UnsupportedOperationException(k.toString());
            }
            return new DoubleValue(r);
        }
    }

    private static class IntegerValue extends Value {
        private final BigInteger i;
        IntegerValue(String s) {
            i = new BigInteger(s);
        }
        IntegerValue(BigInteger i) {
            this.i = i;
        }
        IntegerValue(boolean b) {
            this.i = b? BigInteger.ONE : BigInteger.ZERO;
        }
        @Override
        public String toString() {
            return i.toString();
        }
        @Override
        Value unary(Instruction.Kind k) {
            switch(k) {
                case I2F:
                    return new DoubleValue(i.toString());
                default:
                    throw new UnsupportedOperationException(k.toString());
            }
        }
        @Override
        Value operator(Instruction.Kind k, Value v) {
            BigInteger r;
            IntegerValue that = (IntegerValue) v;
            switch(k) {
                case ADD: r = this.i.add(that.i); break;
                case SUB: r = this.i.subtract(that.i); break;
                case MUL: r = this.i.multiply(that.i); break;
                case MOD: r = this.i.mod(that.i); break;
                case DIV: r = this.i.divide(that.i); break;
                case POW: r = this.i.pow(that.i.intValue()); break;
                case LT: return new IntegerValue(this.i.compareTo(that.i) < 0);
                case LE: return new IntegerValue(this.i.compareTo(that.i) <= 0);
                case EQ: return new IntegerValue(this.i.equals(that.i));
                case NE: return new IntegerValue(!this.i.equals(that.i));
                case OR: return new IntegerValue(this.i.or(that.i));
                default:
                    throw new UnsupportedOperationException(k.toString());
            }
            return new IntegerValue(r);
        }
    }
}
