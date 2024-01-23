public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // When a user wishes to mark a task as completed
    public void markAsDone() {
        this.isDone = true;
    }

    // When user wishes to un-mark a certain task
    public void uncheckingTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
