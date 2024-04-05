package alfred.task;

/**
 * Represents exceptions specific to task operations within a task management application.
 * This class extends the standard Java {@code Exception} class, allowing for the
 * creation of exception instances that carry messages specific to task-related errors.
 */
public class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }
}
