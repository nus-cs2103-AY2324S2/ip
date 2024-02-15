package seedu.banter.tasks;

/**
 * Represents a task.
 */
public abstract class Task { // default access modifier
    /**
     * Represents a task that is done.
     */
    public static final String IS_DONE = "X";

    /**
     * Represents a task that is not done.
     */
    public static final String IS_NOT_DONE = " ";

    private final String description;
    private boolean isDone;

    Task(String description) { // default access modifier
        this.description = description;
        this.isDone = false;
        Assertions.assertTaskIsUnmarked(this);
    }

    Task(String description, boolean isDone) { // default access modifier
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * String representation of a task.
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    String markAsDone() { // default access modifier
        isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    String markAsUndone() { // default access modifier
        isDone = false;
        return "OK, I've marked this task as not done yet:\n  " + this;
    }

    /**
     * Returns the status of a task.
     * @return Status of a task.
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns icon representing a task type.
     * @return Icon representing a task type.
     */
    public abstract String getTaskType();

    /**
     * Returns the description of a task.
     * @return Description of a task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if a task is done.
     * @return True if a task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if the description of the task contains the keyword.
     * @param keyword
     * @return True if the description of the task contains the keyword.
     */
    public boolean contains(String ...keyword) {
        for (String k : keyword) {
            if (!description.contains(k)) {
                return false;
            }
        }
        return true;
    }
}
