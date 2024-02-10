package duke.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description, TaskType.E);
        this.start = start;
        this.end = end;
    }

    @Override
    public String storageString() {
        // Format the task status, task information, and deadline into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[E] | %s | %s | %s | %s", isCompleted, this.getDescription().trim(),
                this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
