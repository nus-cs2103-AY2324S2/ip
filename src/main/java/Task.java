public class Task {
    private boolean isDone = false;
    private String task;
    public Task(String task) {
        this.task = task;
    }
    /*
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }
    /*
     * Mark the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
