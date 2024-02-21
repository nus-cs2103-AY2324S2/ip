package numerator.exceptions.parser;

/**
 * Signals that user input does not follow specified format.
 */
public class InputFormatException extends numerator.exceptions.parser.ParserException {
    /**
     * Constructs an InputFormatException with the specified detail message.
     *
     * @param message should contain information about the correct format.
     */
    public InputFormatException(String message) {
        super(message);
    }
}
