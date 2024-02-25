package exceptions;

//@@author ForAeons-reused
//{Adapted BadDateException}
// with minor modifications
public class BadDateException extends RuntimeException {
    protected final String expected;
    protected final String example;
    protected final String received;

    /**
     * Constructs a BadInputException with the specified detail message.
     *
     * @param message the detail message
     */
    public BadDateException(String message, String expected, String example, String received) {
        super(message);
        this.expected = expected;
        this.example = example;
        this.received = received;
    }

    @Override
    public String getMessage() {

        String message = super.getMessage() + "\n";

        String badInputMessage = String.format("Bad date provided! Check the format or validity of date: %s\n",
                received == null || received.isEmpty() ? "<empty>" : received);

        String expectedMessage = (expected == null) ? "" : String.format("Expected: %s\n", expected);

        String exampleMessage = (example == null) ? "" : String.format("Example: %s\n", example);

        return String.format("%s%s%s%s", badInputMessage, message, expectedMessage, exampleMessage);
    }
}

//@@author ForAeons