package badgpt.exceptions;

import badgpt.tasks.Task;

/**
 * Signals that the specified task is already complete/incomplete.
 */
public class SameStatusException extends TaskException {
    private Task task;
    private int type;

    /**
     * Creates a new SameStatusException with the specified message, task and type. If type is 0, then the attempted
     * operation was to mark it as complete. Otherwise, the attempted operation was to unmark it.
     *
     * @param message The error message.
     * @param task The task which was operated on.
     * @param type The operation performed on the task.
     */
    public SameStatusException(String message, Task task, int type) {
        super(message);
        this.task = task;
        this.type = type;
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return "The task:\n" + task + "\nis " + (type == 0 ? "already complete." : "not yet complete.");
    }
}
