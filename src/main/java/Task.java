public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("    Nice! I have marked this task as done: \n" + "    " + this);
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println("    Ok, I've marked this task as not done yet: \n" + "    " + this);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
