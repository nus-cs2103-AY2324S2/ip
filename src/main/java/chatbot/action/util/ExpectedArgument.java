package chatbot.action.util;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.MissingArgumentValueException;
import chatbot.action.exception.UnexpectedArgumentValueException;

/**
 * This encapsulates the behaviour of an {@link Argument} that a {@link Command} can expect.
 * <li>An ExpectedArgument list generates the usage hint for a {@link Command}.
 *
 * @author Titus Chew
 */
public final class ExpectedArgument extends Argument {

    /**
     * Constructor for this {@link Argument} without a value.
     *
     * @param name The name of this argument, which should not be null.
     */
    public ExpectedArgument(String name) {
        super(name);
    }

    /**
     * Constructor for this {@link Argument} with a value and name.
     *
     * @param name The name of this argument, which should not be null.
     * @param valueUsageName The value of this argument, as a descriptive name of its purpose.
     */
    public ExpectedArgument(String name, String valueUsageName) {
        super(name, valueUsageName);
    }

    /**
     * Validates the supplied {@link Argument} using this expected argument for the presence or lack of a value.
     * <p>
     * If this value is null, then no value is expected.
     *
     * @param command The {@link Command} with that supplied {@link Argument}.
     * @param suppliedArgument The other {@link Argument} to compare with.
     * @throws ActionException If the {@link Argument} has a missing or unexpected value.
     */
    public void validateArgument(Command command, Argument suppliedArgument) throws ActionException {
        if (hasSameArgumentName(suppliedArgument)) {
            if (getValue() != null && suppliedArgument.getValue() == null) {
                throw new MissingArgumentValueException(command, this);
            } else if (getValue() == null && suppliedArgument.getValue() != null) {
                throw new UnexpectedArgumentValueException(command, suppliedArgument);
            }
            // else argument is valid and we do nothing
        }
    }
}
