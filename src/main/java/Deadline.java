public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected String deadline;

    Deadline(String description, String deadline) {
        super(description, TYPE);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return deadline;
    }

    @Override
    public String getFileEncoding() {
        return super.getFileEncoding() + "," + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
