package junjie.exceptions;

/**
 * Represents an exception that is thrown when an invalid argument is provided to a command.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Constructs an invalid argument exception with the given message.
     *
     * @param message The message of the exception.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
