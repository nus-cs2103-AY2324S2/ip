package duke.task;


/**
 * Represents a to-do task.
 *
 * <p>The {@code ToDo} class extends the {@link Task} class and represents a todo task. It contains
 * methods to convert the task to its string representation for storage.</p>
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.ToDo);
    }

    @Override
    public String storageString() {
        // Format the task status, task information, and deadline into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[T] | %s | %s", isCompleted, this.getDescription().trim());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
