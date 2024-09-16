package numerator.exceptions.parser;

import numerator.exceptions.NumeratorException;

/**
 * Signals that an exception has occurred while parsing user input.
 */
public class ParserException extends NumeratorException {
    /**
     * Constructs a ParserException with the specified detail message.
     *
     * @param message should contain information about the exception.
     */
    public ParserException(String message) {
        super(message);
    }
}
