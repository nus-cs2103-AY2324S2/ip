public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws MissMinutesException {
        if (this.isDone) {
            throw new MissMinutesException("Task already marked as done.");
        } else {
            this.isDone = true;
        }
    }

    public void unmark() throws MissMinutesException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new MissMinutesException("Task already marked as undone.");
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}