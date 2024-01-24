public class Deadline extends Task {

    private static final String DEADLINE_MESSAGE = "[D]%s (by: %s)";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_MESSAGE, super.toString(), deadline);
    }

}
