package seedu.duke.task;

/**
 * Represents a task. A <code>Task</code> object corresponds to a task with description and information of
 * whether the task is done.
 */
public class Task {
    private String description;
    private boolean hasDone;

    public Task() {

    }

    /**
     * Constructor of the task
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.hasDone = false;
    }

    /**
     * Returns the description of the task
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task
     *
     * @param description the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getHasDone() {
        return hasDone;
    }


    /**
     * Setter of whether the task is done
     *
     * @param hasDone A boolean variable representing whether the task has done
     */
    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    /**
     * Returns a String representation of the task
     *
     * @return the string repreesntation of the task
     */
    @Override
    public String toString() {
        if (this.hasDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
