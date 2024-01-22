public class Task {
    private boolean isDone;
    private String desc;

    public Task(String desc) {
        this.isDone = false;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void maskAsDone() {
        this.isDone = true;
    }
}
