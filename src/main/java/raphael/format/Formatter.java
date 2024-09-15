package raphael.format;

/**
 * The class in charge of prepending index.
 */
public class Formatter {
    /**
     * Returns the string representation of object o prepended by idx.
     *
     * @param o the object that needs to be formatted
     * @param idx the index that is appended to the object o
     * @return the string representation of object o with index idx prepended
     */
    public static String addIndex(Object o, int idx) {
        return String.format("%d. %s", idx, o);
    }
}
