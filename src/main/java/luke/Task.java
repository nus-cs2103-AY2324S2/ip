package luke;

public abstract class Task {
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

    /**
     * Returns the most recently added task.
     *
     * @return the most recent task
     */
    protected abstract String queryType();

    protected void changeDescription(String newString) {
        this.description = newString;
    }

    protected void changeFrom(String newString) {};

    protected void changeTo(String newString) {};

    protected void changeBy(String newString) {};
}