import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSymbolTable {
    @Test
    public void test() {
        SymbolTable<Type> table = new SymbolTable<Type>();
        // Store a global variable
        table.put("MAX", Type.INT);
        assertEquals(Type.INT, table.get("MAX"));

        table.enter(); // Outer local scope
        table.put("n", Type.STRING);
        assertEquals(Type.STRING, table.get("n"));
        assertEquals(Type.INT, table.get("MAX"));

        table.enter(); // Nested(inner) local scope (within the above one)
        table.put("n", Type.FLOAT);
        //System.out.println(table.maps);
        assertEquals(Type.FLOAT, table.get("n"));
        assertEquals(Type.INT, table.get("MAX"));
        table.leave(); // Exit inner local

        assertEquals(Type.STRING, table.get("n"));
        assertEquals(Type.INT, table.get("MAX"));
        table.leave(); // Exit outer local

        assertNull(table.get("n"));
        assertEquals(Type.INT, table.get("MAX"));
    }
}
