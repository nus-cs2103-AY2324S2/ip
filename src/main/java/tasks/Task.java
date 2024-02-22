package tasks;

/**
 * Represents a Task.
 */
public class Task {
    private boolean isDone;
    private String name;
    /**
     * Task constructor.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Getter method for name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for status.
     */
    public String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.name;
    }

    public String fileString() {
        return "| " + (this.isDone ? "1" : "0") + " | " + this.name;
    }
}
