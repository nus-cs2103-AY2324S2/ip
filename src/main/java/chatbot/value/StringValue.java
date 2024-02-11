package chatbot.value;

/**
 * This encapsulates a value which must be a {@link String}.
 *
 * @author Titus Chew
 */
public class StringValue {
    /** The {@link String} value stored. */
    private final String stringValue;

    /**
     * Constructor for this.
     *
     * @param value the value as a {@link String}, which is not null.
     */
    public StringValue(String value) {
        assert value != null;
        this.stringValue = value.trim();
    }

    /**
     * Gets a human-readable {@link String} of this.
     *
     * @return the human-readable {@link String}
     */
    @Override
    public String toString() {
        return stringValue;
    }
}
