package homie;

/**
 * TodoException class. Handles all exceptions related to to-do tasks.
 * Thrown when no description is given when creating to-do task.
 */
public class TodoException extends Exception {
    public TodoException(String message) {
        super("Bruh... " + message + "\nPlease follow this format: \ntodo {TODO_DESCRIPTION}");
    }
}
