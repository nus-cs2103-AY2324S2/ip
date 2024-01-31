package osiris.task;

/**
 * Represents a to-do task in the Osiris application.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask object with the specified task name.
     *
     * @param taskName The name of the to-do task.
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a ToDoTask object with the specified task name and completion status.
     *
     * @param taskName    The name of the to-do task.
     * @param isCompleted The completion status of the to-do task.
     */
    public ToDoTask(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    /**
     * Retrieves the string representation of the to-do task for storage.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String getStringStorageRepresentation() {
        return String.format("T | %s", super.getStringStorageRepresentation());
    }

    /**
     * Retrieves the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

}
