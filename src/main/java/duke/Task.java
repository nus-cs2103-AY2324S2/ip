package duke;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default provided constructor for Task.
     * <p>
     * Subclasses should call this constructor to initialize the description and {@code isDone} field.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     *
     * The string representation should be a sequence of valid commands for the program,
     * separated by newlines,
     * such that if there was originally taskIndex tasks in the list,
     * then after executing the string representation,
     * there will be taskIndex + 1 tasks in the list.
     *
     * @param taskIndex the 0-indexed index of this task.
     * @return the serialized string.
     */
    public abstract String serializeToCommand(int taskIndex);

    /**
     * Determines if the current task clashes with the other given task.
     *
     * @param otherTask the other task.
     */
    public abstract boolean isClashingWith(Task otherTask);

    protected String serializeDoneMark(int taskIndex) {
        if (this.isDone) {
            return "mark " + (taskIndex + 1) + "\n";
        } else {
            return "";
        }
    }
}
