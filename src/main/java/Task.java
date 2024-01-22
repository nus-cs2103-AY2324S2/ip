public abstract class Task {
    private boolean done;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", taskName);
    }

}
