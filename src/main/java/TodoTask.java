/**
 * Represents a task to be done. A <code>ToDoTask</code>
 * object contains its description or name, represented by a String, and
 * a boolean indicating whether the task is marked.
 */
public class TodoTask extends Task {

    /**
     * Basic Constructor
     * @param taskName name of task to be tracked
     */
    public TodoTask(String taskName) {
        super(taskName);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
