package zoe;

/**
 * Superclass for all forms of tasks
 * Contains some global methods so that all subclasses have access to that method
 */
public class Task {
    protected static int DONE_STATE = 1;
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
        this.description = description.trim();
        this.type = "";
        this.isDone = false;
    }

    /**
     * Returns the marked condition X or empty to supplement the marking command
     */
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

    /**
     * Returns the status of the respective task for visualisation in the GUI
     */
    public String getStatus() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.description);
    }

    /**
     * Records whether a task is done or not in numerical form for data loading
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

    /**
     * Returns a priority to sort the tasks in the following order: todos, events, deadlines according to date
     */
    public int getPriority() {
        return priority;
    }
}