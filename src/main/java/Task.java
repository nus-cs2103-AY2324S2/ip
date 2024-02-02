/**
 * The Task class is an abstraction for representing tasks in real life,
 * where a task could either be done or not done.
 *
 * @author Chai Ming How
 * @since 2024-02-01
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTaskIcon();
    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription(){
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getTaskIcon() + "]" + "[" + getStatusIcon() + "] " + description;
    }
}