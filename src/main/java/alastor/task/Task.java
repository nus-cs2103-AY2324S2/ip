package alastor.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toFile() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return this.description.equals(other.description)
                    && this.isDone == other.isDone;
        }
        return false;
    }
}

