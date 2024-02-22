package chatbot.value;

/**
 * This encapsulates a value which must be a {@link String}.
 * <p>
 * All subclasses must implement a constructor that takes in a {@link String}.
 *
 * @author Titus Chew
 */
public class StringValue {
    /** The {@link String} value stored. */
    private final String stringValue;

    /**
     * Constructor for this.
     *
     * @param value The value as a {@link String}, which is not null.
     */
    public StringValue(String value) {
        assert value != null;
        this.stringValue = value.trim();
    }

    /**
     * Gets a human-readable {@link String} of this.
     */
    @Override
    public String toString() {
        return stringValue;
    }
}
