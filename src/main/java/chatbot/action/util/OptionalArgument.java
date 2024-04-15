package chatbot.action.util;

import chatbot.value.StringValue;

/**
 * Represents an argument of a command that can be expected, but is optional.
 * <li>Optional arguments are wrapped in {@code [square brackets]} to indicate that they are optional.
 * <li>The default argument is never an {@link OptionalArgument}.
 *
 * @author Titus Chew
 */
public final class OptionalArgument extends ExpectedArgument {
    /**
     * Constructor for this {@link Argument} without a value.
     *
     * @param name The name of this argument, which should not be null.
     */
    public OptionalArgument(String name) {
        super(name);
    }

    /**
     * Constructor for this {@link Argument} with a value and name.
     *
     * @param name The name of this argument, which should not be null.
     * @param valueUsageName The value of this argument, as a descriptive name of its purpose.
     * @param expectedType The expected type of the value of this argument, which is a subtype of StringValue.
     */
    public OptionalArgument(String name, String valueUsageName, Class<? extends StringValue> expectedType) {
        super(name, valueUsageName, expectedType);
    }

    @Override
    public String toString() {
        return "[" + super.toString() + "]";
    }
}
