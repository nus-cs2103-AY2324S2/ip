package task;

/**
 * Represents a task that needs to be done.
 */
public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Marks the task as Done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[ ] %s", this.name);
        }
    }
}