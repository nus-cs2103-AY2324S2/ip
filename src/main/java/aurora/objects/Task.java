package aurora.objects;

/** The Task class represents the various different tasks in the task list of the application. */
public class Task {

    /** Status denoting if a task is done. */
    private boolean isDone;

    /** Description of the task. */
    private String description;

    private static final String DONE_BRACKET = "[X]";
    private static final String NOT_DONE_BRACKET = "[ ]";

    /**
     * Constructs a Task object.
     *
     * @param description: Description of task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String representation of the Task object to be saved to the storage file.
     *
     * @return String representation of the Task object to be saved to the storage file.
     */
    public String toFileString() {
        return this.description;
    }

    @Override
    public String toString() {
        String statusBracket;
        if(this.getStatus()) {
            statusBracket = DONE_BRACKET;
        } else {
            statusBracket = NOT_DONE_BRACKET;
        }
        String taskString = statusBracket + " " + this.description;
        return taskString;
    }
}
