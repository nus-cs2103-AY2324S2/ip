package cat.ui.response;

/**
 * A response given when an error is encountered in the program.
 */
public class ErrorResponse extends Response {
    /**
     * Constructs a response for an error.
     */
    public ErrorResponse(Exception e) {
        super("The cat tilts its head and hands you an error report", e.toString());
        setOutputColor(Colors.BRIGHT_RED);
    }

    /**
     * Constructs a response for an error manually from a string.
     */
    public ErrorResponse(String e) {
        super("The cat tilts its head and hands you an error report", e);
        setOutputColor(Colors.BRIGHT_RED);
    }
}
