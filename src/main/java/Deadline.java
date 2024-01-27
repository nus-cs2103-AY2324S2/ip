public class Deadline extends Task {
    private final String deadlineDateTime;
    public Deadline(String description, String deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadlineDateTime + ")";
    }

    @Override
    public String serializeTask() {
        return "D | " + super.serializeTask() + " | " + this.deadlineDateTime;
    }
}
