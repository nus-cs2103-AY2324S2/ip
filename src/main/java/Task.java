public class Task {
    protected String description;
    protected boolean isDone;

    String indent = "    ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println(indent + "This task is already done");
        } else {
            isDone = true;;
        }

    }

    public void markAsUndone() {
        if (isDone) {
            isDone = false;;
        } else {
            System.out.println(indent + "This task is not done yet");
        }

    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
