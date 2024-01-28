public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "Nice! I've marked this task as done:\n  [X] " + description;
        }
        else {
            return "OK, I've marked this task as not done yet:\n  [ ] " + description;
        }
    }

}
