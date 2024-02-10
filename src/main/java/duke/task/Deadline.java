package duke.task;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description, TaskType.D);
        this.deadline = deadline;
    }

    @Override
    public String storageString() {
        // Format the task status, task information, and deadline into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[D] | %s | %s | %s", isCompleted, this.getDescription().trim(),
                this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
