package task;

/**
 * This class is the basic of all Tasks in the TodoList.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert description != null;
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        assert description != null:
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * return the signed status in X or not.
     * 
     * @return status.
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}