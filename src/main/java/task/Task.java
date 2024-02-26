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

    public String markAsDone() {
        this.isDone = true;
        String output = "Nice! I've marked this task as done:\n";
        output += this.toString();
        return output;
    }

    public String unmark() {
        this.isDone = false;
        String output = "OK, I've marked this task as not done yet:";
        output += this.toString();
        return output;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}