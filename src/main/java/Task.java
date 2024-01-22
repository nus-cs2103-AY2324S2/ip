public class Task {
    private String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    private String getStatusIcon() {
        return (this.done ? "X" : " ");
    }

    public String taskWithStatus() {
        return String.format("[%s] %s", getStatusIcon(), this);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
