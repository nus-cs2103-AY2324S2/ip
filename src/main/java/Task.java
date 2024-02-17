public class Task {
    private String description;
    private Boolean isDone;

    public Task(String name) {
        this.description = name;
        this.isDone = false;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

    public String toFileString() {
        return (this.isDone ? "1": "0") + " | " + this.description;
    }
}
