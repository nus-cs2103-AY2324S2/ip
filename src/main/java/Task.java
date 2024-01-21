public class Task {
    protected String desc;
    protected Boolean completed;

    public Task(String desc) {
        this.desc = desc;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " "); // mark X if task is completed
    }

    public void isCompleted() {
        this.completed = true;
    }

    public void isNotCompleted() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desc);
    }
}
