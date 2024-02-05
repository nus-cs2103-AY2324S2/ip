package shon.task;

/**
 * Represents a todo task in the <code>TaskList</code>.
 */
public class Todo extends Task {
    /**
     * Creates a new todo with the given description and completion status.
     *
     * @param description The description of the <code>Todo</code> task.
     * @param isDone The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the <code>Todo</code>.
     *
     * @return [T] to indicate <code>Todo</code>, completion status marked by X if done and task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the data of the task to be stored. T represents <code>Todo</code>, 0/1 to represent completion
     * status. Fields are separated by "|".
     *
     * @return The data of the task formatted to be stored.
     */
    @Override
    public String formatData() {
        return "T" + " | " + this.formatTask();
    }
}
