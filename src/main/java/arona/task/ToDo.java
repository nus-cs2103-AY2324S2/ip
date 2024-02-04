package arona.task;

/**
 * Represents a "To Do" task,
 * which is a task with no time attached.
 *
 * @author Maximilliano Utomo
 */
public class ToDo extends Task {
    /**
     * A public constructor for the task.Todo.
     * @param desc - the description of the task
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toDataFormat() {
        return "T|" + super.toDataFormat();
    }

    /**
     * Represent the task into a String format applicable for printing output.
     * Uses an extra [T] to represent a task.Todo.
     * @return A String representation of the task.Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
