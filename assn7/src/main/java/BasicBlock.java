import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a sequence of straight-line IR instructions.
 */
public class BasicBlock implements Iterable<Instruction> {

    private ArrayList<Instruction> list = new ArrayList<>();
    private HashMap<String, Integer> strings = new HashMap<>();
    private int nextStringNum = 1;

    /**
     * Append a new instruction onto the end of the block.
     */
    public BasicBlock add (Instruction i) {
        list.add(i);
        for(Address a : i.operands) {
            if(a.kind == Address.Kind.CONST && a.type == Type.STRING) {
                strings.put(a.value, nextStringNum++);
            }
        }
        return this;
    }

    public Iterator<Instruction> iterator() {
        return list.iterator();
    }

    public Integer getStringID(String s) {
        return strings.get(s);
    }

    public Set<String> getStrings() {
        return strings.keySet();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(Instruction i : list) {
            b.append(i).append('\n');
        }
        return b.toString();
    }

}
