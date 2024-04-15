package chatbot.action.util;

import chatbot.action.exception.ActionException;
import chatbot.action.exception.MissingArgumentValueException;
import chatbot.action.exception.UnexpectedArgumentValueException;
import chatbot.value.StringValue;

/**
 * This encapsulates the behaviour of an {@link Argument} that a {@link Command} can expect.
 * <li>An ExpectedArgument list generates the usage hint for a {@link Command}.
 *
 * @author Titus Chew
 */
public class ExpectedArgument extends Argument {
    /** Stores the type of the value of this. */
    private Class<? extends StringValue> expectedType;

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
     * @param expectedType The expected type of the value of this argument, which is a subtype of StringValue.
     */
    public ExpectedArgument(String name, String valueUsageName, Class<? extends StringValue> expectedType) {
        super(name, valueUsageName);
        this.expectedType = expectedType;
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
    public void validateArgumentValue(Command command, Argument suppliedArgument) throws ActionException {
        if (hasSameArgumentName(suppliedArgument)) {
            if (getValue() != null && suppliedArgument.getValue() == null) {
                throw new MissingArgumentValueException(command, this);
            } else if (getValue() == null && suppliedArgument.getValue() != null) {
                throw new UnexpectedArgumentValueException(command, suppliedArgument);
            }
            // else argument is valid and we do nothing
        }
    }

    public Class<? extends StringValue> getExpectedType() {
        return expectedType;
    }
}
