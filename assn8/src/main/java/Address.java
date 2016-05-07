
/** An address in three-address code is either a constant (of any type),
 * a temporary (represented by a unique integer), or a source-language
 * variable (represented by a string giving its name).
 */
class Address {
    enum Kind {
        VAR, CONST, TEMP, LABEL
    }

    Kind kind;
    Type type;
    String value;  // Data value for CONST; variable name for VAR; null otherwise
    int serialNum; // Only used for TEMP

    private static int nextTempId = 1;

    /**
     * Create a new CONST or VAR with the given type.
     * @param value The constant value for CONST or the variable name for VAR.
     */
    Address(Kind kind, Type type, String value) {
        assert kind != Kind.TEMP;
        this.kind = kind;
        this.type = type;
        this.value = value;
    }

    /**
     * Create a new temporary of the given type.
     */
    Address(Type type) {
        this.kind = Kind.TEMP;
        this.type = type;
        this.serialNum = nextTempId++;
    }

    /**
     * Create a label, which doesn't have a type.
     */
    Address() {
        this.kind = Kind.LABEL;
        this.type = null;
        this.serialNum = nextTempId++;
    }

    /**
     * Display the address as its type along with its identity or value.
     * Temporaries are prefixed by 't' and variable names by 'v'.
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(kind != Kind.LABEL) {
            b.append(type).append(' ');
        }
        switch (kind) {
            case TEMP:
                b.append('t').append(serialNum);
                break;
            case LABEL:
                b.append('L').append(serialNum);
                break;
            case VAR:
                b.append('v').append(value);
                break;
            case CONST:
                b.append(value);
                break;
            default:
                throw new UnsupportedOperationException(kind.toString());
        }
        return b.toString();
    }
}
