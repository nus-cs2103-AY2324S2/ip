package ellie.task;

/**
 * Represents a to-do task without any date/time attached to it.
 * Example: visit new theme park
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object with the given description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task (1 for done, 0 for not done).
     */
    public Todo(String description, int isDone) {
        super(description, isDone);
    }

    /**
     * Returns the task type identifier for a to-do task.
     *
     * @return The task type identifier ('T' for to-do).
     */
    @Override
    public char getTaskType() {
        return 'T';
    }

    /**
     * Returns a formatted string representation of the to-do task for listing.
     *
     * @return A formatted string representation of the to-do task, including completion status and description.
     */
    @Override
    public String listTaskString() {
        return "[T]" + super.listTaskString();
    }

}
