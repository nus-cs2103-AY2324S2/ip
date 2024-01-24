public class Task {
    protected final String name;
    protected boolean complete;

    Task(String name) {
        this.name = name;
        this.complete = false;
    }

    boolean getCompletionStatus() {
        return this.complete;
    }
    void toggleCompletion() {
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
}
