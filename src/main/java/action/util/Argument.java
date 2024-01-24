package action.util;

/**
 * Argument stores a {@link Command} argument's name, and it's associated value.
 * <ul>
 * <li>The argument must have a name.
 * <li>If the name matches the command, it is defined to be the default argument.
 *
 * @author Titus Chew
 */
public class Argument {
    final String name, value;

    /**
     * Constructor for this argument without a value
     *
     * @param name the name of this argument, which should not be null
     */
    public Argument(String name) {
        this.name = name;
        // null represents the value that isn't there
        this.value = null;
    }

    /**
     * Constructor for this argument with a value and name.
     *
     * @param name the name of this argument, which should not be null
     * @param value the value of this argument, which can be null
     */
    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets this argument name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of this argument.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Checks if the other argument has the same name as this.
     *
     * @param otherArgument the other argument to compare with
     * @return true if the name is the same, otherwise false
     * @see #hasSameArgumentName(String)
     */
    public boolean hasSameArgumentName(Argument otherArgument) {
        return name.equals(otherArgument.name);
    }

    /**
     * Checks if the other argument has the same name as this.
     *
     * @param otherArgumentName the other argument name, as a string, to compare with
     * @return true if the name is the same, otherwise false
     * @see #hasSameArgumentName(Argument)
     */
    public boolean hasSameArgumentName(String otherArgumentName) {
        return name.equals(otherArgumentName);
    }
}
