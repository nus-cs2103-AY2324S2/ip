package hal.task;

/**
 * The Todo class represents a task without any specific deadline.
 */
public class Todo extends Task {
    private static final String DIVIDER = " | ";

    /**
     * Constructs a new Todo object.
     *
     * @param isDone      Indicates if the task is done.
     * @param description The description of the task.
     */
    public Todo(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns a string representing the task suitable for saving to a file.
     *
     * @return A string representing the task for file storage.
     */
    @Override
    public String getFileString() {
        return "T" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description;
    }
}

