public class Task {
    protected String task;
    protected Boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public Boolean getStatus() { return this.isDone;}

    public void setStatus(Boolean b) {
        this.isDone = b;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
