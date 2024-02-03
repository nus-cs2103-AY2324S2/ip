package duke.exception;

/**
 * Signals an error caused by not specifying task name when creating a new task.
 */
public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException() {
        super("Sorry! Please describe the task that you want me to add.\nButo is not a mind reader!");
    }
}
