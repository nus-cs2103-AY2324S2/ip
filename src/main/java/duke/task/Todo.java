package duke.task;

/**
 * The Todo class represents a task without any specific deadline or time frame.
 * It extends the Task class and provides specific implementations for task-related methods.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given task description and completion status.
     *
     * @param taskDescription The description of the todo task.
     * @param isCompleted     The completion status of the todo task.
     */
    public Todo(String taskDescription, boolean isCompleted) {
        super(taskDescription, isCompleted);
    }

    /**
     * Gets the task icon associated with a todo task.
     *
     * @return The task icon, which is "[T]" for todo tasks.
     */
    @Override
    public String getTaskIcon() {
        return "[T]";
    }

    /**
     * Gets the status icon associated with a todo task.
     *
     * @return The status icon, which is "[X]" if the task is completed, "[ ]" otherwise.
     */
    @Override
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    /**
     * Gets the formatted description of the todo task.
     *
     * @return The trimmed task description without the "todo" keyword.
     */
    @Override
    public String getTaskDescription() {
        return trimDescription(taskDescription);
    }

    /**
     * Trims the task description by removing the "todo" keyword.
     *
     * @param taskDescription The original task description.
     * @return The trimmed task description without the "todo" keyword.
     */
    @Override
    protected String trimDescription(String taskDescription) {
        return taskDescription.replaceAll("(?i)todo", "").trim();
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }
}
