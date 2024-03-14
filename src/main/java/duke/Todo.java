package duke;

/**
 * Represents a "todo" task in the Duke application.
 * A "todo" task is a basic task type that consists of a description
 *      and a completion status but does not have an associated time.
 */
public class Todo extends Task {
    /**
     * The task type identifier for todo tasks.
     */
    private static final String TASK_TYPE = "T";

    /**
     * Constructs a new Todo instance.
     *
     * @param description The description of the todo task.
     *      It should provide a brief summary of the task to be accomplished.
     * @param isDone The initial completion status of the todo task.
     *      True indicates that the task has been completed, while false indicates that it is still pending.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates and returns a string representation of the todo task formatted for file storage.
     * The format follows a convention suitable for easy file parsing, consisting of the task type indicator ('T'),
     * the completion status, and the task description, separated by vertical bars.
     *
     * @return A string formatted for saving the todo task to a file,
     *      including its type, completion status, and description.
     */
    @Override
    public String toFileFormat() {
        return TASK_TYPE + " | " + this.isDone + " | " + this.description;
    }

    /**
     * Provides a string representation of the todo task for display purposes.
     * This representation includes the task type indicator ('[T]'),
     *      the completion status (marked as '[X]' for done and '[ ]' for not done),
     *      and the task description.
     *
     * @return A string representation of the todo task,
     *      including its type, completion status, and description, suitable for display.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
