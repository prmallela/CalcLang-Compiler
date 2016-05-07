import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a sequence of straight-line IR instructions.
 */
class BasicBlock implements Iterable<Instruction> {

    private final ArrayList<Instruction> list = new ArrayList<>();

    /**
     * Append a new instruction onto the end of the block.
     */
    public BasicBlock add (Instruction i) {
        list.add(i);
        return this;
    }

    public Iterator<Instruction> iterator() {
        return list.iterator();
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
