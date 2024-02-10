package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.T);
    }

    @Override
    public String storageString() {
        // Format the task status, task information, and deadline into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[T] | %s | %s", isCompleted, this.getDescription().trim());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
