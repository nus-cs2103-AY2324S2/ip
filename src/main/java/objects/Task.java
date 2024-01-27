package objects;

public class Task {
    private final String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");

    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    };


    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}
