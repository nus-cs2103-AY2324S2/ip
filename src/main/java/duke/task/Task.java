package duke.task;

/**
 * Represents an abstract task in the Duke application. This class is a base for various types of tasks.
 */
public abstract class Task {

    protected String fullTaskDescription; //include timeline
    protected String description;
    protected boolean isDone;

    /**
     * Returns a string representation of the task's completion status.
     * @return A string "X" if the task is completed, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Marks this task as complete.
     */
    public void markComplete() {
        this.isDone = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task showing its completion status.
     */
    @Override
    public String toString() {
        String str;
        str = "[" + this.getStatusIcon() + "] ";
        return str;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * @return A string suitable for saving the task to a file.
     */
    public abstract String toSave();

}
