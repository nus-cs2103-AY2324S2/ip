package lex.ui;

/**
 * Represents the user interface.
 */
public class Ui {
    private final StringBuffer buffer;

    /**
     * Constructor for the Ui class.
     *
     * @param buffer The buffer.
     */
    public Ui(StringBuffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Prints the given message.
     *
     * @param message The message.
     */
    public void print(String message) {
        assert message != null : "Message should not be null";

        buffer.append(message).append("\n");
    }

    /**
     * Returns the buffer and clears it.
     *
     * @return The String currently in the buffer.
     */
    public String flush() {
        assert buffer != null : "Buffer should not be null";

        var value = buffer.toString();
        buffer.setLength(0);
        return value;
    }
}
