public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, int isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.description + " (by: " + stringToDate(this.deadline) + ")";
    }
}
