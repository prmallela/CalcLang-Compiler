import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

class IRGraph implements Iterable<Integer> {
    private final TreeMap<Integer, BasicBlock> graph = new TreeMap<>();
    private BasicBlock block;
    private final Address startLabel;

    IRGraph() {
        startLabel = new Address();
        block = new BasicBlock();
        add(startLabel, block);
    }

    public void newBlock(Address label) {
        block = new BasicBlock();
        add(label, block);
    }

    void add(Address label, BasicBlock bb) {
        assert label.kind == Address.Kind.LABEL;
        graph.put(label.serialNum, bb);
    }

    void add(Instruction instr) {
        block.add(instr);
    }

    Integer startLabel() {
        return startLabel.serialNum;
    }

    @Override
    public Iterator<Integer> iterator() {
        return graph.keySet().iterator();
    }

    BasicBlock getBlock(int label) {
        return graph.get(label);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(Integer L : graph.keySet()) {
            b.append('L').append(L).append(":\n");
            b.append(graph.get(L));
        }
        return b.toString();
    }

    public static void main(String[] args) {
        Address i1 = new Address(Address.Kind.CONST, Type.INT, "1");
        Address L1 = new Address();
        Address L2 = new Address();
        Instruction k1 = new Instruction(Instruction.Kind.BRANCH, i1, L1, L2);
        System.out.println(k1);
    }
}
