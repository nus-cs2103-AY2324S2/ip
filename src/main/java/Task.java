public class Task {
    /**
     * Status denoting if a task is done.
     */
    private boolean status;

    /**
     * Description of the task.
     */
    private String description;

    /**
     * Constructor for the Task class.
     * @param description of task
     */
    public Task(String description) {
        this.status = false;
        this.description = description;
    }

    /**
     * Getter for the status of a task.
     * @return true if the task is done, false if the task is not done.
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Set the status of the task to "done".
     */
    public void setDone() {
        this.status = true;
    }

    /**
     * Set the status of the task to "done".
     */
    public void setNotDone() {
        this.status = false;
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
