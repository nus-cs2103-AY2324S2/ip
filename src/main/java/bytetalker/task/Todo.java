package bytetalker.task;

/**
 * Represents the Todo task that the user wants to store.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-02-06
 */
public class Todo extends Task {
    public Todo(String task) {
        super(TaskType.TODO, task);
    }

    public Todo(String task, boolean isDone) {
        super(TaskType.TODO, task, isDone);
    }

    /**
     * Creates a string to show information(task type, status, task content) about the todo task.
     *
     * @return String that contains information about task.
     */
    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask();
    }
}
