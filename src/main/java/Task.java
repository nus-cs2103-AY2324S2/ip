public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public String toString() {
        return String.format("[%s] %s\n", this.done ? "X" : " ", this.name);
    }
}
