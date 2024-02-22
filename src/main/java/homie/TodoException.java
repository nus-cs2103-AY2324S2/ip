package homie;

/**
 * To do Exception class. Thrown when there is an error creating to do task.
 */
public class TodoException extends Exception {
    public TodoException(String message) {
        super(message);
    }
}
