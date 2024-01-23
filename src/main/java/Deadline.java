public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[D][" + statusIcon + "] " + description + " (by: " + deadline + ")";
    }
}
