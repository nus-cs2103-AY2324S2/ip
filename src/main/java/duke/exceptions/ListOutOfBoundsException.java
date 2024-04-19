package duke.exceptions;

/**
 * Exception class representing an error when trying to access an element at an invalid index in a list.
 * This exception is thrown when attempting to access an element at an index that is out of bounds.
 */
public class ListOutOfBoundsException extends Exception {

    /**
     * Constructs a ListOutofBoundsException with the specified detail message.
     *
     * @param message The detail message indicating the invalid list index.
     */
    public ListOutOfBoundsException(String message) {
        super(message);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message indicating that the list index is out of bounds.
     */
    @Override
    public String getMessage() {
        return "    You have entered an invalid list index:\n"
                + "    List size:" + super.getMessage();
    }
}
