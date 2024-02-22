package ping.job;
import java.io.Serializable;
// Solution below adapted by week2 iP Level-3 Partial solution

/**
 * This class is used to create a task
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    /**
     * This is the constructor of the Task class
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unMarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
