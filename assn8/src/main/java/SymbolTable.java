import java.util.HashMap;
import java.util.Stack;

public class SymbolTable<T> {

    Stack<HashMap<String, T>> maps = new Stack<>();

    public SymbolTable() {
        maps.push(new HashMap<String,T>()); // Empty global scope
    }

    public void put(String name, T t) {
        maps.peek().put(name, t);
    }

    public T get(String name) {
        // Need to search the stack from top to bottom
        // (from inner to outer scopes)
        for (int i = maps.size() - 1; i >= 0; i--) {
            HashMap<String, T> scope = maps.get(i);
            T t = scope.get(name);
            if (t != null) return t; // Found it!
        }
        return null; // Not found
    }

    public void enter() {
        maps.push(new HashMap<String,T>()); // New empty local scope
    }

    public void leave() {
        maps.pop();
    }
}
