import org.antlr.v4.runtime.ANTLRInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class IRtoLLVM {

    private static final String TEMPLATE = "template.ll";
    private static final String SAMPLE = "guess.calc";
    private final PrintStream out;
    private final HashSet<String> allocatedVars
            = new HashSet<>();
    private final HashMap<String, Integer> strings
            = new HashMap<>();

    public IRtoLLVM(IRGraph graph, PrintStream out) throws IOException {
        this.out = out;
        collectStrings(graph);
        InputStream is = getClass().getResourceAsStream(TEMPLATE);
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line = rd.readLine();
        while(line != null) {
            if(line.matches(".*<<code>>")) {
                emitGraph(graph);
            }
            else if(line.matches(".*<<strings>>")) {
                for(Map.Entry<String, Integer> entry : strings.entrySet()) {
                    out.printf("@str%d = private unnamed_addr constant [%d x i8] c\"%s\\00\", align 1\n",
                            entry.getValue(),
                            entry.getKey().length()+1,
                            entry.getKey().replace("\n", "\\0A")
                    );
                }
            }
            else {
                out.println(line);
            }
            line = rd.readLine();
        }
        is.close();
    }

    private static final String FMT_INT = "%ld\n";
    private static final String FMT_FLOAT = "%.6le\n";
    private static final String STR_TRUE = "True";
    private static final String STR_FALSE = "False";

    private void collectStrings(IRGraph graph) {
        int nextStringNum = 1;
        strings.put(FMT_INT, nextStringNum++);
        strings.put(FMT_FLOAT, nextStringNum++);
        strings.put(STR_TRUE, nextStringNum++);
        strings.put(STR_FALSE, nextStringNum++);
        for(int label : graph) {
            for(Instruction instr : graph.getBlock(label)) {
                for(Address a : instr) {
                    if(a.type == Type.STRING && a.kind == Address.Kind.CONST) {
                        strings.put(a.value, nextStringNum++);
                    }
                }
            }
        }
    }

    private void emitGraph(IRGraph graph) {
        out.printf("Lstart:\n");
        out.printf("  call void @initRuntime()\n");
        boolean first = true;
        for(int label : graph) {
            if(first) {
                out.printf("  br label %%L%d\n", label);
                first = false;
            }
            out.printf("L%d:\n", label);
            emitBlock(graph.getBlock(label));
        }
    }

    private void emitBlock(BasicBlock block) {
        for(Instruction instr : block) {
            emitInstruction(instr);
        }
    }

    private String getElementPtr(String s) {
        int n = s.length() + 1;
        return String.format(
                "getelementptr inbounds ([%d x i8], [%d x i8]* @str%d, i32 0, i32 0)",
                n, n, strings.get(s)
        );
    }

    private void emitInstruction(Instruction instr) {
        switch(instr.kind) {
            case PRINT: {
                Type t = instr.dest().type;
                switch (t) {
                    case INT:
                        out.printf("  call i32 (i8*, ...) @printf(i8* %s, %s)\n",
                                getElementPtr(FMT_INT),
                                loadAddrWithType(instr.dest()));
                        break;
                    case FLOAT:
                        out.printf("  call i32 (i8*, ...) @printf(i8* %s, %s)\n",
                                getElementPtr(FMT_FLOAT),
                                loadAddrWithType(instr.dest()));
                        break;
                    case BOOL: {
                        Address s = new Address(Type.STRING);
                        out.printf("  %s = select %s, i8* %s, i8* %s\n",
                                addr(s),
                                loadAddrWithType(instr.dest()),
                                getElementPtr(STR_TRUE),
                                getElementPtr(STR_FALSE));
                        out.printf("  call i32 @puts(%s)\n",
                                addrWithType(s));
                        break;
                    }
                    case STRING:
                        out.printf("  call i32 @puts(%s)\n",
                                loadAddrWithType(instr.dest()));
                        break;
                    default:
                        throw new UnsupportedOperationException(t.toString());
                }
                // Flush output stream after each print statement
                Address tmp = new Address(Type.INT);
                out.printf("  %s = load %%struct.__sFILE*, %%struct.__sFILE** @__stdoutp, align 8\n",
                        addr(tmp));
                out.printf("  call i32 @fflush(%%struct.__sFILE* %s)\n",
                        addr(tmp));
                break;
            }
            case END:
                out.printf("  ret i32 0\n");
                break;
            case ADD:
            case MUL:
            case DIV:
            case MOD:
            case SUB:
            case LT:
            case LE:
            case EQ:
            case NE:
            case OR:
                out.printf("  %s = %s %s, %s\n",
                        addr(instr.dest()),
                        opcodeFor(instr.kind, instr.first().type),
                        loadAddrWithType(instr.first()),
                        loadAddr(instr.second()));
                break;
            case POW:
            case LOG:
            case SCONCAT:
                out.printf("  %s = %s(%s, %s)\n",
                        addr(instr.dest()),
                        opcodeFor(instr.kind, instr.dest().type),
                        loadAddrWithType(instr.first()),
                        loadAddrWithType(instr.second()));
                break;
            case READLINE:
            case RANDOM:
                out.printf("  %s = %s()\n",
                        addr(instr.dest()),
                        opcodeFor(instr.kind, instr.dest().type));
                break;
            case SQRT:
            case I2S:
            case S2I:
                out.printf("  %s = %s(%s)\n",
                        addr(instr.dest()),
                        opcodeFor(instr.kind, instr.dest().type),
                        loadAddrWithType(instr.first()));
                break;
            case I2F:
                out.printf("  %s = sitofp %s to %s\n",
                        addr(instr.dest()),
                        loadAddrWithType(instr.first()),
                        type(Type.FLOAT));
                break;
            case FLOOR: {
                Address tmp = new Address(Type.FLOAT);
                out.printf("  %s = call double @floor(%s)\n",
                        addr(tmp),
                        loadAddrWithType(instr.first()));
                out.printf("  %s = fptosi %s to i64\n",
                        addr(instr.dest()),
                        loadAddrWithType(tmp));
                break;
            }
            case STORE: {
                if(!allocatedVars.contains(instr.dest().value)) {
                    out.printf("  %s = alloca %s\n",
                            addr(instr.dest()),
                            type(instr.dest().type));
                    allocatedVars.add(instr.dest().value);
                }
                out.printf("  store %s, %s* %s\n",
                        loadAddrWithType(instr.first()),
                        type(instr.dest().type),
                        addr(instr.dest()));
                break;
            }
            case BRANCH: {
                out.printf("  br %s, %s, %s\n",
                        addrWithType(instr.dest()),
                        addr(instr.second()),
                        addr(instr.first()));
                break;
            }
            case JUMP: {
                out.printf("  br %s\n",
                        addr(instr.dest()));
                break;
            }
            default:
                throw new UnsupportedOperationException(instr.kind.toString());
        }
    }

    private String opcodeFor(Instruction.Kind k, Type t) {
        switch(k) {
            case ADD: return t == Type.FLOAT? "fadd" : "add nsw";
            case MUL: return t == Type.FLOAT? "fmul" : "mul";
            case DIV: return t == Type.FLOAT? "fdiv" : "sdiv";
            case SUB: return t == Type.FLOAT? "fsub" : "sub";
            case LT: return t == Type.FLOAT? "fcmp olt" : "icmp slt";
            case LE: return t == Type.FLOAT? "fcmp ole" : "icmp sle";
            case EQ: return t == Type.FLOAT? "fcmp oeq" : "icmp eq";
            case NE: return t == Type.FLOAT? "fcmp une" : "icmp ne";
            case MOD: return "srem"; // signed remainder
            case OR: return "or";
            case SQRT: return "call double @sqrt";
            case LOG: return "call double @logWithBase";
            case READLINE: return "call i8* @readLine";
            case RANDOM: return "call double @randomDouble";
            case POW: return t == Type.FLOAT?
                    "call double @llvm.pow.f64" :
                    "call i64 @intPow";
            case SCONCAT: return "call i8* @strConcat";
            case S2I: return "call i64 @parseInt";
            case I2S: return "call i8* @showInt";
            default:
                throw new UnsupportedOperationException(
                        k.toString() + " " + t.toString());
        }
    }

    private String addr(Address addr) {
        switch(addr.kind) {
            case CONST:
                if(addr.type == Type.STRING) {
                    return getElementPtr(addr.value);
                }
                else {
                    return addr.value;
                }
            case TEMP:
                return "%t" + addr.serialNum;
            case VAR:
                return "%v" + addr.value;
            case LABEL:
                return "label %L" + addr.serialNum;
            default:
                throw new UnsupportedOperationException(addr.kind.toString());
        }
    }

    private String addrWithType(Address addr) {
        return type(addr.type) + " " + addr(addr);
    }

    private String loadAddr(Address addr) {
        if(addr.kind == Address.Kind.VAR) {
            Address tmp = new Address(addr.type);
            out.printf("  %s = load %s, %s* %s\n",
                    addr(tmp),
                    type(addr.type),
                    type(addr.type),
                    addr(addr));
            addr = tmp;
        }
        return addr(addr);
    }

    private String loadAddrWithType(Address addr) {
        return type(addr.type) + " " + loadAddr(addr);
    }

    private String type(Type type) {
        switch(type) {
            case INT: return "i64";
            case FLOAT: return "double";
            case BOOL: return "i1";
            case STRING: return "i8*";
            default:
                throw new UnsupportedOperationException(type.toString());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        InputStream is;
        if(args.length >= 1) {
            is = new FileInputStream(args[0]);
        }
        else {
            is = ConvertToIR.class.getResourceAsStream(SAMPLE);
        }
        TypeChecker tc = new TypeChecker(new ANTLRInputStream(is));
        if(tc.getNumErrors() == 0) {
            ConvertToIR ir = new ConvertToIR(tc);
            System.out.println(ir.graph);
            new IRtoLLVM(ir.graph, System.out);
            System.out.println("\n\n========");
            InterpretLLVM.runInteractively(ir.graph);
        }

    }

}
