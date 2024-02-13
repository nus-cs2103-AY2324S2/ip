package tasks;

/**
 * Represents a Todo task, extending the base Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified task name.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName) {
        super(taskName, "T");
    }

    /**
     * Constructs a Todo task with the specified task name and status.
     *
     * @param taskName   The name of the task.
     * @param isTaskDone The status of the task (0 for not done, 1 for done).
     */
    public Todo(String taskName, int isTaskDone) {
        super(taskName, "T");
        changeStatus(isTaskDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string containing task details.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + super.getTaskName();
    }
}
