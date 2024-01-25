public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws HenryException {
        if (description.isBlank()) {
            throw new HenryException("No description of task!");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws HenryException {
        if (this.isDone) {
            throw new HenryException("This was already marked.");
        }
        this.isDone = true;
    }

    public void unmarkAsDone() throws HenryException {
        if (!this.isDone) {
            throw new HenryException("This was already unmarked.");
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
