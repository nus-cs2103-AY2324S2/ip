public class Deadline extends Task {
    private String deadline;
    private TaskType type;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.type = TaskType.D;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", type, super.toString(), deadline);
    }
}
