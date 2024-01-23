package action;

/**
 * Argument is a data class that stores a command argument's name, and it's associated value.
 * The argument must have a name.
 * If the name matches the command, it is defined to be the default argument.
 *
 * @author Titus Chew
 */
public final class Argument {
    public final String name, value;

    /**
     * Constructor for an argument without a value.
     * @param name the name of the argument, which should not be null.
     */
    public Argument(String name) {
        // null represents the name that isn't there
        this.name = name;
        this.value = null;
    }

    /**
     * Constructor for an argument with a value and name.
     * @param name the name of the argument, which should not be null.
     * @param value the value of the argument, which can be null.
     */
    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
