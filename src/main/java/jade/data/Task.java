package jade.data;

import java.time.LocalDate;

/**
 * The <code>Task</code> object represents a user task.
 */
public class Task {
    protected String description; // the description of the task
    protected boolean isDone; // the completion status of the task, if true then is done

    /**
     * Class constructor specifying the task description.
     * The task is not done by default.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Another Class constructor specifying the task description and completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the icon of the current completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string of the completion status boolean value.
     * If is done then returns "1", otherwise returns "0".
     */
    public String statusFormatter() {
        return (isDone ? "1" : "0");
    }

    /**
     * Modifies the completion status as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Modifies the completion status as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the two dates are the same.
     * The Task class has no date, so the methods returns false by default.
     */
    public boolean isSameDate(LocalDate dateTime) {
        return false;
    }

    /**
     * Returns the formatted task string to be saved in the local file.
     */
    public String taskFormatter() {
        return String.format("T | %s | %s\n", statusFormatter(), description);
    }

    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
