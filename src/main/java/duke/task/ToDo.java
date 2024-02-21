package duke.task;

/**
 * Represents a to-do task without any specific date or time attached. It extends the {@code Task} class,
 * representing the simplest form of a task.
 */
public class ToDo extends Task{
    
    /**
     * Constructs a {@code ToDo} task with the specified description.
     *
     * @param description The textual description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }
    
    /**
     * Returns a string representation of the to-do task, including its type and status.
     *
     * @return A formatted string indicating the task's type (T for ToDo), completion status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
