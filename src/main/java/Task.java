public class Task {
    private String description;
    private boolean isComplete;

    // Private constructor to create a new Task with the given description
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    // The description of the task
    public String getDescription() {
        return description;
    }

    // Mark task as done
    public void markAsDone() {
        this.isComplete = true;
    }

    // Unmark task as done
    public void markAsUndone() {
        this.isComplete = false;
    }

    // True if the task is done, else no
    public boolean isDone() {
        return isComplete;
    }

    // To string
    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + description;
    }
}