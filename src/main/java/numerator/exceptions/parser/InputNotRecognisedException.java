package numerator.exceptions.parser;

/**
 * Signals that user input is not recognised.
 */
public class InputNotRecognisedException extends numerator.exceptions.parser.ParserException {
    /**
     * Constructs an InputNotRecognisedException with the specified detail message.
     */
    public InputNotRecognisedException() {
        super("Input not recognised");
    }
}
