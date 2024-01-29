/**
 * Represents a task to be done. A <code>ToDoTask</code>
 * object contains its description or name, represented by a String, and
 * a boolean indicating whether the task is marked.
 */
public class TodoTask extends Task {
    /**
     * Basic Constructor
     * @param taskname name of task to be tracked
     */
    public TodoTask(String taskname) {
        super(taskname);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
