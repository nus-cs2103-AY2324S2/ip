public class Task {
    protected String desc;
    protected Boolean completed;

    public Task(String desc) {
        this.desc = desc;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (this.completed ? "X" : " "); // mark X if task is completed
    }

    public String getTaskName() {
        return this.desc;
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

    public String fileString() {
        return String.format("%s | %s", this.getStatusIcon().equals("X") ? "COMPLETED" : "INCOMPLETE", this.desc);
    }
}
