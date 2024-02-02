package duke.task;

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
     * Returns string representative of Task.
     * The string consist of symbol, mark status,
     * description, and deadline date.
     *
     * @return String representative of Task.
     */
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
}
