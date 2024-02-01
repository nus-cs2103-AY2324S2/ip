
package duke.task;
public abstract class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toString();

    public String type() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}