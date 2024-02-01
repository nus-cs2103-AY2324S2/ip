package chatbot.value;

import chatbot.value.exception.InvalidValueTypeException;

/**
 * This minimally represents a {@link String},
 * but <b>must</b> also represent an {@link Integer} no matter what.
 *
 * @author Titus Chew
 */
public final class IntegerStringValue extends StringValue {
    /** Stores the value as an {@link Integer}. */
    private final Integer integerValue;

    /**
     * Takes in a string, but converts it to an {@link Integer}.
     *
     * @param value the value as a {@link String}
     * @throws InvalidValueTypeException If the value cannot be converted.
     */
    public IntegerStringValue(String value) throws InvalidValueTypeException {
        super(value);

        Integer i = null;
        try {
            i = Integer.parseInt(super.toString());
        } catch(NumberFormatException e) {
            // invalid integer
            throw new InvalidValueTypeException("integer", value);
        } finally {
            this.integerValue = i;
        }
    }

    /**
     * Factory method for taking in a {@link StringValue}, but tries to convert it to an {@link Integer}.
     *
     * @param value the value as a {@link StringValue}
     * @throws InvalidValueTypeException If the value cannot be converted.
     */
    public IntegerStringValue(StringValue value) throws InvalidValueTypeException {
        this(value.toString());
    }

    /**
     * Tries to get the int value.
     *
     * @return the value as an int
     * @throws InvalidValueTypeException If the value cannot be converted.
     */
    public int tryGetIntegerValue() throws InvalidValueTypeException {
        if (integerValue == null) {
            throw new InvalidValueTypeException("integer", super.toString());
        }
        return integerValue;
    }
}
