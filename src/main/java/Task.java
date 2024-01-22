public class Task {
    private String task;
    private boolean isDone;

    public Task(String input) {
        this.task = input;
        this.isDone = false;
    }

    public void check() {
        this.isDone = true;
    }

    public void uncheck() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }
}
