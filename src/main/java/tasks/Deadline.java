package tasks;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String[] parts) {
        super(parts[0]);
        this.type = TaskType.DEADLINE;
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input for deadline task. Expected: <description> /by <dueDate>");
        }
        this.dueDate = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate + ")";
    }

    @Override
    public String getType() {
        return type.toString();
    }
}