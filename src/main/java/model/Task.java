package model;

public class Task {
    private final String title;
    private boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public String isDoneString() {
        if (this.isDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}
