/**
 * Argument encapsulates a ChatBot command argument's name, and it's associated value.
 * The argument may not have a name, and in that case, is defined to be the default argument.
 * All other arguments in an Action should have a name.
 *
 * @author Titus Chew
 */
public class Argument {
    private final String name, value;

    /**
     * Constructor for an argument without a name.
     * @param value the value of the argument
     */
    public Argument(String value) {
        // null represents the name that isn't there
        this.name = null;
        this.value = value;
    }

    /**
     * Constructor for an argument with a value and name.
     * @param name the name of the argument
     * @param value the value of the argument
     */
    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Checks if the other name matches the argument name.
     * @param otherName the name to compare with
     * @return true if the name matches, otherwise false.
     */
    public boolean hasMatchingName(String otherName) {
        if (otherName == null) {
            // null represents the name of a default argument (without a name)
            return name == null;
        } else {
            return otherName.equals(name);
        }
    }

    /**
     * @return the value of the argument
     */
    public String getValue() {
        return value;
    }
}
