package yoda.exceptions;
/**
 * Exception thrown when an attempt is made to create a task with an empty description.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs an EmptyDescriptionException with a detailed message.
     * This exception is typically thrown when a task is created without a 
     * necessary description.
     *
     * @param message A string providing details about the exception cause.
     */
    public EmptyDescriptionException(String message) {
        super(message); // Call to the superclass (Exception) constructor with the detailed message
    }
}
