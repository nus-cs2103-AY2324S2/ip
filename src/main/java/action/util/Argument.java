package action.util;

import action.exception.ActionException;
import action.exception.MissingArgumentValueException;
import action.exception.UnexpectedArgumentValueException;

/**
 * Argument stores a {@link Command} argument's name, and it's associated value.
 * <ul>
 * <li>The argument must have a name.
 * <li>If the name matches the command, it is defined to be the default argument.
 *
 * @author Titus Chew
 */
public final class Argument {
    private final String name, value;

    /**
     * Constructor for this argument without a value.
     *
     * @param name the name of this argument, which should not be null.
     */
    public Argument(String name) {
        this.name = name;
        // null represents the value that isn't there
        this.value = null;
    }

    /**
     * Constructor for this argument with a value and name.
     *
     * @param name the name of this argument, which should not be null.
     * @param value the value of this argument, which can be null.
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
     */
    public boolean hasSameArgumentName(Argument otherArgument) {
        return name.equals(otherArgument.name);
    }

    /**
     * Validates the supplied argument for the presence or lack of a value.
     * <p>
     * If this value is null, then no value is expected.
     *
     * @param command the command with that supplied argument
     * @param suppliedArgument the other argument to compare with
     * @throws ActionException If the argument has a missing or unexpected value.
     */
    public void validateArgument(Command command, Argument suppliedArgument) throws ActionException {
        if (hasSameArgumentName(suppliedArgument)) {
            if (value != null && suppliedArgument.value == null) {
                throw new MissingArgumentValueException(command, this);
            } else if (value == null && suppliedArgument.value != null) {
                throw new UnexpectedArgumentValueException(command, suppliedArgument);
            }
        }
    }
}
