package tasks;

public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;

    public Task(String task) {
        this.taskName = task;
    }

    public void markTask() {
        this.isCompleted = true;
    }
    public void unmarkTask() {
        this.isCompleted = false;
    }
    public abstract String saveFormat();
    @Override
    public String toString() {
        String check = isCompleted ? "[X]" : "[ ]";
        return check + " " + this.taskName;
    }
}
