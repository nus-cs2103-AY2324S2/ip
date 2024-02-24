package lia;

/**
 * The Todo class represents a task without any specific deadline or duration.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */
    public Todo(String description, boolean isDone, boolean isImp) {
        super(description, isDone, isImp);
    }

    /**
     * Returns a string representation of the Todo task, including task icons and status.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "]" + "[" + getStatusIcon() + "]" + "[" + getImpIcon() + "] " + getDescription();
    }
}
