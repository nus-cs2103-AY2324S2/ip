package duke.task;

// Generic duke.task.Task
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String fileString() { return (isDone ? "1 | " : "0 | ") + this.name; }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }
}

