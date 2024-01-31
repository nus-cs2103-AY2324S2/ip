public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, Boolean status, String deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String convertToFileFormat() {
        return "D | " + super.convertToFileFormat() + " | " + this.deadline;
    }

}
