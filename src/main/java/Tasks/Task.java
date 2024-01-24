package Tasks;

public abstract class Task {
    public String task;
    protected boolean completed;

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String completedIcon() {
        return this.completed ? "X" : " ";
    }

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    public abstract String getType();

    public abstract String getAdditionalInfo();

    public abstract String toString();
}
