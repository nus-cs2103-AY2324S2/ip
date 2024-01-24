package action.util;

import action.exception.ActionException;
import action.exception.MissingArgumentValueException;
import action.exception.UnexpectedArgumentValueException;

/**
 * ExpectedArgument encapsulates the behaviour of arguments that a command can expect.
 * <ul>
 * <li>An ExpectedArgument list generates the usage hint for a command.
 *
 * @author Titus Chew
 */
public final class ExpectedArgument extends Argument {

    /**
     * Constructor for this argument without a value.
     *
     * @param name the name of this argument, which should not be null
     */
    public ExpectedArgument(String name) {
        super(name);
    }

    /**
     * Constructor for this argument with a value and name.
     *
     * @param name  the name of this argument, which should not be null
     * @param valueUsageName the value of this argument, as a descriptive name of its purpose
     */
    public ExpectedArgument(String name, String valueUsageName) {
        super(name, valueUsageName);
    }

    /**
     * Validates the supplied argument using this expected argument for the presence or lack of a value.
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
