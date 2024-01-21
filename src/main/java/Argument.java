/**
 * Argument encapsulates a ChatBot command argument's name, and it's associated value.
 *
 * @author Titus Chew
 */
public class Argument {
    private final String name, value;

    /**
     * Constructor for an argument without a name
     * @param value the value of the argument
     */
    public Argument(String value) {
        this.name = "";
        this.value = value;
    }

    /**
     * Constructor for an argument with a value and name
     * @param name the name of the argument
     * @param value the value of the argument
     */
    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name of the argument
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of the argument
     */
    public String getValue() {
        return value;
    }
}
