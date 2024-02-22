package drake;

/**
 * Exception thrown when an attempt to create a todo task is made without specifying a description.
 * This is a subclass of StringIndexOutOfBoundsException, which is typically thrown to indicate that
 * an index is either negative or greater than the size of the string.
 */
public class TodoLeftBlankException extends StringIndexOutOfBoundsException {
    public TodoLeftBlankException(String message) {
        super(message);
    }
}
