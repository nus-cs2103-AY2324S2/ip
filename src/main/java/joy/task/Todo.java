package joy.task;

/**
 * Represents a Todo task in the task list.
 * A Todo task is a basic task without a specific deadline or event time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);

    }

    /**
     * Returns a string representation of the Todo task for saving to a file.
     *
     * @return A formatted string representing the Todo task for saving to a file.
     */
    @Override
    public String toFileString() {
        // Format: T | 0/1 | description
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @param t The task to be checked
     * @return if the task is a duplicate
     */
    @Override
    public boolean equals(Task t) {
        if (!(t instanceof Todo)) {
            return false;
        }

        return t.toString().equals(this.toString());
    }
}
