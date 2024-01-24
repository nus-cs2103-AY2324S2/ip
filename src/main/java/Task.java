public class Task {
    private final String name;
    private boolean complete;

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

    private String completionDisplay() {
        if (complete) {
            return "[X]";
        }
        return "[ ]";
    }
    public String toString() {
        return String.format("%s %s", this.completionDisplay(), this.name);
    }
}
