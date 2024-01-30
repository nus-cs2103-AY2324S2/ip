public class Deadline extends Task {

    private static final String TYPE_SYMBOL = "[D]";
    private final String deadline;

    public Deadline(String description, String deadline) throws MeanDukeException {
        super(description, TYPE_SYMBOL);
        if (deadline.isEmpty()) {
            throw new MeanDukeException("Usage: \"add deadline <description> /by <deadline>\"");
        }
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, TYPE_SYMBOL, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }
}
