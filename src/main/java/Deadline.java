public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description + "(by: " + this.deadline + ")";
    }
}
