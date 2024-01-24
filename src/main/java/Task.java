public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    public void undone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format(
                "[%c] %s",
                this.isDone ? 'X' : ' ',
                this.name
        );
    }
}
