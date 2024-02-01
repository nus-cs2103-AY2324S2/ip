package tasks;
public class Task {
    protected final String name;
    protected boolean complete;

    public Task(String name) {
        this.name = name;
        this.complete = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletionStatus() {
        return this.complete;
    }
    public void toggleCompletion() {
        if (complete) {
            complete = false;
            return;
        }
        complete = true;
    }

    protected String completionDisplay() {
        if (complete) {
            return "[X]";
        }
        return "[ ]";
    }

    protected String taskTypeDisplay() {
        return "[this shouldn't show]";
    }
    public String toString() {
        return String.format("%s%s %s", this.taskTypeDisplay(), this.completionDisplay(), this.name);
    }
    public String storeFormat() {
        String completeFormat = complete ? "1" : "0";
        return String.format("%s | %s | %s", "Err", completeFormat, name);
    }
}
