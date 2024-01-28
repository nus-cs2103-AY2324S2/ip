package bytebuddy.tasks;

/**
 * The Todo class represents a simple task without a specified deadline or duration.
 * It extends the Task class and provides specific implementations for task creation and string representations.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with completion status, description, and optional information.
     *
     * @param completed   The completion status of the task (1 for done, 0 for not done).
     * @param description The description of the task.
     */
    public Todo(String completed, String description) {
        super(description, completed.equals("1"));
    }

    /**
     * Returns a formatted string representation of the Todo task for writing into output file.
     *
     * @return The formatted output string.
     */
    @Override
    public String textFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("T | %d | %s", intIsDone, description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}