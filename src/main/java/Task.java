

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String toString2() {
        String check = isDone ? "[X] " : "[ ] ";
        return check + this.name;
    }

    public void done() {
        this.isDone = true;
    }

    public void undone() {
        this.isDone = false;
    }
}
