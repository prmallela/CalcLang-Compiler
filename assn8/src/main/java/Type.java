/**
 * An enumeration representing all base types in Calc. It also includes
 * the ERROR designation, used by the type checker to propagate errors.
 */
public enum Type {
    INT,
    FLOAT,
    BOOL,
    STRING,
    ERROR;

    /**
     * The types form a hierarchy, with ERROR at the root.
     * @return The least upper bound of this and that.
     */
    public Type leastUpperBound(Type that) {
        if(this == that)
            return this;
        if((this == INT && that == FLOAT)
            ||(this == FLOAT && that == INT))
            return FLOAT;
        return ERROR;
    }

    /**
     * Determines whether the type is numeric, defined as INT or FLOAT.
     */
    public boolean isNumeric() {
        return leastUpperBound(FLOAT) == FLOAT;
    }

    /**
     * Determines whether the type is numeric or BOOL.
     */
    public boolean isNumericOrBool() {
        return isNumeric() || this == BOOL;
    }
}
