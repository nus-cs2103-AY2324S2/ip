package exception.gronk;

/**
 * EmptyListException class.
 * This exception is thrown when the user tries to
 * call the `list` command before any tasks are added
 * to the list.
 */
public class EmptyListException extends Exception {
    public EmptyListException() {}

    public EmptyListException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "Nothing added yet!";
    }
}
