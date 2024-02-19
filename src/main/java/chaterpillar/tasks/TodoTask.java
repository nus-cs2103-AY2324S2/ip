package chaterpillar.tasks;

/**
 * Represents a task to be done. A <code>ToDoTask</code>
 * object contains its description or name, represented by a String, and
 * a boolean indicating whether the task is marked.
 *
 * @author marclamp
 */
public class TodoTask extends Task {

    /**
     * Basic Constructor
     * @param taskName name of task to be tracked
     */
    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Overloaded Constructor with isMarked status
     *
     * @param taskName name of task to be tracked
     * @param isMarked status of task (marked or unmarked)
     */
    public TodoTask(String taskName, Boolean isMarked) {
        super(taskName, isMarked);
    }
    @Override
    public String formatStringForSaving() {
        return "T|" + super.formatStringForSaving();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
