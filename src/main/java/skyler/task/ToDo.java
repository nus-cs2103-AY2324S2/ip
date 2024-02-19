package skyler.task;

public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      The completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task, including its type and
     * description.
     *
     * @return The formatted string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}