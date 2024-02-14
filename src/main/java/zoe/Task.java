package zoe;

/**
 * Superclass for all forms of tasks
 * Contains some global methods so that all subclasses have access to that method
 */
public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    protected int priority;

    public Task() {
        this.description = "";
        this.type = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.type = "";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done to reflect in zoe
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a given task from list in zoe
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.description);
    }

    /**
     * Records whether a task is done or not for data loading
     */
    public int isDoneNumerical() {
        //to help with saving and loading
        if (this.isDone) {
            return 1;
        }

        return 0;
    }

    /**
     * Saves respective tasks in the required format
     */
    public String saveTask() {
        return String.format("%d|%s", this.isDoneNumerical(), this.description);
    }

    public int getPriority() {
        return priority;
    }
}