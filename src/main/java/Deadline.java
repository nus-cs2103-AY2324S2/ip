public class Deadline extends Task {
    protected String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return "by: " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + getDeadline() + ")";
    }
}
