package chatbot.value.exception;

import chatbot.exception.ChatBotException;

/**
 * This represents exceptions due to an invalid argument type.
 *
 * @author Titus Chew
 */
public final class InvalidValueTypeException extends ChatBotException {
    /** Stores the name of the expected type. */
    private final String expectedTypeName;

    /** Stores the value that is of the invalid type. */
    private final String invalidTypeValue;

    /**
     * Constructor for an exception with a value type that doesn't match it's expected type,
     * so is invalid.
     */
    public InvalidValueTypeException(String expectedTypeName, String invalidTypeValue) {
        this.expectedTypeName = expectedTypeName;
        this.invalidTypeValue = invalidTypeValue;
    }

    @Override
    public String getMessage() {
        return String.format("`%s` is not an %s.", invalidTypeValue, expectedTypeName);
    }
}
