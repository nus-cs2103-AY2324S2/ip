public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    public boolean isComplete() {
        return this.isDone;
    }

    public void complete() {
        this.isDone = true;
    }

    public void uncomplete() {
        this.isDone = false;
    }

    public String saveTask() {
        return this.toString();
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
