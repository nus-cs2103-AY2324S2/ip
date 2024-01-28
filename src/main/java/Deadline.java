public class Deadline extends Task {
    private final DateTime deadlineDateTime;
    public Deadline(String description, DateTime deadlineDateTime)  {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadlineDateTime.formatDateTime() + ")";
    }

    @Override
    public String serializeTask() {
        return "D | " + super.serializeTask() + " | " + this.deadlineDateTime.serializeDateTime();
    }
}
