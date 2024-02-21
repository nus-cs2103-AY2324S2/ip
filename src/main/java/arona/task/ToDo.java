package arona.task;

/**
 * Represents a "To Do" task,
 * which is a task with no time attached.
 *
 * @author Maximilliano Utomo
 */
public class ToDo extends Task {
    /**
     * A public constructor for the task.ToDo.
     * @param desc - the description of the task
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Represent the task into a String format applicable for saving data.
     * Uses an extra T| to represent a task.ToDo.
     * @return A data representation of the task.ToDo
     */
    @Override
    public String toDataFormat() {
        return "T|" + super.toDataFormat();
    }

    /**
     * Represent the task into a String format applicable for printing output.
     * Uses an extra [T] to represent a task.ToDo.
     * @return A String representation of the task.ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
