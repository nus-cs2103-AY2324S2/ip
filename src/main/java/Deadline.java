public class Deadline extends Task {
    private static final String id = "[D]";
    private final String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return id + super.toString() + "(by: " + this.deadline + ")";
    }
}
