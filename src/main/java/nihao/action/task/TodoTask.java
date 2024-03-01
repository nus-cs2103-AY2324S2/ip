package nihao.action.task;

/**
 * Represents a to-do task.
 */
public class TodoTask extends Task {
    /**
     * Constructor that specifies the name of the task.
     */
    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Returns the String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TodoTask && ((TodoTask) obj).taskName.equals(taskName);
    }
}
