package homie;

/**
 * TodoException thrown when no description is given when creating Todo task.
 */
public class TodoException extends Exception {
    public TodoException(String message) {
        super("Bruh... " + message + "\nPlease follow this format:\ntodo {TODO_DESCRIPTION}");
    }
}
