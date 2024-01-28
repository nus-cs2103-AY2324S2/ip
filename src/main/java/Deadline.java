public class Deadline extends Task {
    private final String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
    @Override
    public String toFileFormat() {
        return String.format("D %s %s", super.toFileFormat(), this.deadline);
    }
}
