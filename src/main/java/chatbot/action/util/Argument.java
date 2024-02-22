package chatbot.action.util;

import java.util.Optional;

import chatbot.value.StringValue;

/**
 * This stores a {@link Command} argument's name, and it's associated value.
 * <li>The argument must have a name.
 * <li>If the name matches the command, it is defined to be the default argument.
 * <li>The default argument doesn't have a "{@code /}" at the front.
 *
 * @author Titus Chew
 */
public abstract class Argument {
    /** Stores the name of this */
    private final String name;

    /** Stores the value of this */
    private StringValue value;

    /**
     * Constructor for this argument without a value
     *
     * @param name The name of this argument, which should not be null.
     */
    public Argument(String name) {
        assert name != null;
        this.name = name;
        // null represents the value that isn't there
        this.value = null;
    }

    /**
     * Constructor for this argument with a value and name.
     *
     * @param name The name of this argument, which should not be null.
     * @param value The value of this argument, which can be null.
     */
    public Argument(String name, String value) {
        assert name != null;
        this.name = name;
        this.value = (value == null) ? null : new StringValue(value);
    }

    public String getName() {
        return name;
    }

    public StringValue getValue() {
        return value;
    }

    void setValue(StringValue stringValue) {
        value = stringValue;
    }

    /**
     * Checks if the other argument has the same name as this.
     *
     * @param otherArgument The other argument to compare with.
     * @return True if the name is the same, otherwise false.
     * @see #hasSameArgumentName(String)
     */
    boolean hasSameArgumentName(Argument otherArgument) {
        return name.equals(otherArgument.name);
    }

    /**
     * Checks if the other argument has the same name as this.
     *
     * @param otherArgumentName The other argument name, as a string, to compare with.
     * @return True if the name is the same, otherwise false.
     * @see #hasSameArgumentName(Argument)
     */
    public boolean hasSameArgumentName(String otherArgumentName) {
        return name.equals(otherArgumentName);
    }

    /**
     * Returns the human-readable string of this.
     */
    @Override
    public String toString() {
        return ("/" + getName() + " " + Optional.ofNullable(getValue())
                .map(val -> "<" + val + ">")
                .orElse(""))
                .trim();
    }
}
