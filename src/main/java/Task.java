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

    public boolean markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    public boolean markUndone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.getStatusIcon(),
                this.description
        );
    }
}
