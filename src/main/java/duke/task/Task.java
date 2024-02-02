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
    public String getStatusIcon() { //method to get the mark status of task
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the mark status.
     *
     */
    public void mark() { //method to mark the status
        isDone = true;
    }

    /**
     * Unmarks the mark status.
     *
     */
    public void unMark() { //method to unmark the status
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
    public String toString(){ //method to get the string representation of task
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
    public String toWrite(){ //method to get the string representation of task
        String s = this.getStatusIcon() + " | " + this.description;
        return s;
    }
}
