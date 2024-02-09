package mona;

/**
 * This class represents a task which is the superclass of all other tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task
     * @param description the description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void setCompletion(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    public String parseToLogRepresentation() {
        return this.toString();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}
