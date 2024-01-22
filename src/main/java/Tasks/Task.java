package Tasks;

public abstract class Task {
    private boolean isDone;
    private String desc;

    public Task(String desc) {
        this.isDone = false;
        this.desc = desc;
    }

    public boolean isDone() {
        return isDone;
    }
    public String getDesc() {
        return this.desc;
    }

    public abstract String getStatusIcon();

    public void maskAsDone() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public abstract String toString();
}
