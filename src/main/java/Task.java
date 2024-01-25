public abstract class Task {
    boolean done;
    String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), done ? "X" : " ", taskName);
    }
}