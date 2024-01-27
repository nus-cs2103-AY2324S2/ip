public class Deadline extends Task {
    private String deadline;

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toSave() {
        return "[D]|" + super.toSave() + "|" + deadline;
    }
}
