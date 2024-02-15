package luke;

public class Task {
    protected boolean done;
    protected String description;

    public Task (String description) {
        this.done = false;
        this.description = description;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public void setToDone() {
        this.done = true;
    }

    public void setToNotDone() {
        this.done = false;
    }

    /**
     * Returns the description of the task.
     * @return description of the task
     */
    public String getDescription() { return this.description; }
}