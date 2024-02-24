package yue.tasks;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object with the given task description.
     *
     * @param task The task description.
     */
    public TodoTask(String task) {
        super(task);
    }


    /**
     * Returns the tag for the todo task.
     *
     * @return The tag for the todo task.
     */
    @Override
    public String tag() {
        return "[T]";
    }


    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return tag() + mark() + " " + task;
    }
}
