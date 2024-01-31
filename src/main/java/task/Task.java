package task;

public class Task {
    protected String description; // task description
    protected boolean completed; // true is task is completed

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    public String toString() {
        if (this.completed) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
