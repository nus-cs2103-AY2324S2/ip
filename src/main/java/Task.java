public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescriptionStatus() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

    public String getMarkStatus() {
        return (isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }
}
