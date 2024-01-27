public class Deadline extends Task {

    private static final String DEADLINE_MESSAGE = "[D]%s (by: %s)";
    private static final String DEADLINE_FILE_TEMPLATE = "D | %s | %s";
    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String taskFileTemplate() {
        return String.format(DEADLINE_FILE_TEMPLATE, super.taskFileTemplate(), deadline);
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_MESSAGE, super.toString(), deadline);
    }

}
