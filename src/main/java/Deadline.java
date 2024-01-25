public class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (isDone ? "X" : " ") + "] " + description + "(by: "
                + deadline + ")";
    }
}
