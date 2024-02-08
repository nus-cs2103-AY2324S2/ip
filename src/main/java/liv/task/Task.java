package liv.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void changeStatus() {
        isDone = !isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDisplayedString() {
        return getStatusIcon() + " " + getDescription();
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String serializeTask() {
        return null;
    }

    /**
     * Checks if a string is in the description of the task.
     * @param other The string to check.
     * @return True if the string other is in the description of task, False otherwise.
     */
    public boolean isInDescription(String other) {
        return this.description.contains(other);
    }
}
