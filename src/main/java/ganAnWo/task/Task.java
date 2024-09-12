package ganAnWo.task;

/**
 * Class for basic task.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns mark status in String format.
     *
     * @return mark status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the mark status.
     *
     */
    public void setMark() {
        isDone = true;
    }

    /**
     * Unmarks the mark status.
     *
     */
    public void setUnMark() {
        isDone = false;
    }

    /**
     * Returns true if the key given is contained in
     * the description of the task.
     *
     * @param key the string key need to be contained.
     * @return a boolean whether the key is contained.
     */
    public boolean hasFind(String key) {
        if (description.contains(key)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    /**
     * Returns string representative of Task with write format.
     * The string consist of symbol, mark status,
     * description, and deadline date.
     *
     * @return String representative of Task.
     */
    public String toWrite() {
        String s = this.getStatusIcon() + " | " + this.description;
        return s;
    }

    /**
     * Check whether the task instance
     * is equal to other task instance.
     *
     * @param task Task instance to be checked.
     * @return boolean result of the check.
     */
    public boolean isEqual(Task task) {
        return description.equals(description);
    }
}
