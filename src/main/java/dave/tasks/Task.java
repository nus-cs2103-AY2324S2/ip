package dave.tasks;

public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark X if task is completed
    }

    public String getTaskName() {
        return this.desc;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desc);
    }

    public String fileString() {
        return String.format("%s | %s", this.getStatusIcon().equals("X") ? "COMPLETED" : "INCOMPLETE", this.desc);
    }
}
