package luke.task;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task, which takes in a description of the task and sets the task as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task with either an X if the task is done or a space if the task is not done.
     *
     * @return The status of the task in String format.
     */
    private String printStatus() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;

    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns true if the task description contains the keyword, and false otherwise.
     *
     * @param keyword The keyword to be checked against the task description.
     * @return True if the task description contains the keyword, and false otherwise.
     */
    public boolean matchKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns the string representation of the task to be saved in the data file.
     *
     * @return The string representation of the task to be saved in the data file.
     */
    public String toDataString() {
        return (this.isDone ? "X" : "O") + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[" + printStatus() + "] " + this.description;
    }

}
