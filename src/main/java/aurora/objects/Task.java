package aurora.objects;

/**
 * The Task class represents the various different tasks in the task list of the application.
 */
public class Task {

    /** Status denoting if a task is done. */
    private boolean isDone;

    /** Description of the task. */
    private String description;

    /**
     * Constructor for the Task class.
     *
     * @param description: Description of task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Getter for the status of a task.
     *
     * @return true if the task is done, false if the task is not done.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Set the status of the task to "done".
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Set the status of the task to "done".
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Getter for the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task object to a fileString that can be saved.
     *
     * @return String representing the task object.
     */
    public String toFileString() {
        return this.description;
    }

    @Override
    public String toString() {
        String statusBracket;
        if(this.getStatus()) {
            statusBracket = "[X]";
        } else {
            statusBracket = "[ ]";
        }
        String taskString = statusBracket + " " + this.description;
        return taskString;
    }
}
