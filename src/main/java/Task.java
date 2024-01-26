public class Task {
    private final boolean isComplete;
    private final String content;

    Task(String content) {
        this.isComplete = false;
        this.content = content;
    }

    Task(boolean isComplete, String content)
    {
        this.isComplete = isComplete;
        this.content = content;
    }

    public Task markDone() {
        return new Task(true, this.content);
    }
    public Task unmarkTask() {
        return new Task(false, this.content);
    }
    public String toString() {
        if (this.isComplete) {
            return String.format("[X] %s", this.content);
        }
        return String.format("[ ] %s", this.content);
    }
}
