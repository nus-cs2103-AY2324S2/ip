package chatbot.value.exception;

import chatbot.exception.ChatBotException;

/**
 * This represents exceptions due to an invalid argument type.
 *
 * @author Titus Chew
 */
public final class InvalidValueTypeException extends ChatBotException {
    /**
     * Stores the name of the type.
     */
    private final String expectedType;

    /**
     * Stores the value that is of the invalid type.
     */
    private final String invalidTypeValue;

    /**
     * Constructor for this with an invalid value type.
     *
     * @param expectedType the name of the expected type
     * @param invalidTypeValue the value of the invalid type as a {@link String}
     */
    public InvalidValueTypeException(String expectedType, String invalidTypeValue) {
        this.expectedType = expectedType;
        this.invalidTypeValue = invalidTypeValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return String.format("`%s` is not an %s.", invalidTypeValue, expectedType);
    }
}
