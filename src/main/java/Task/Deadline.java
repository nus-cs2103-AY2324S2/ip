package task;

public class Deadline extends Task {
    private TaskDateTime dueTime;

    public Deadline(String description, String due) throws DukeDateTimeParseException {
        super(description);
        this.dueTime = new TaskDateTime(due);
    }

    public Deadline(String description, String due, boolean isDone) throws DukeDateTimeParseException {
        super(description, isDone);
        this.dueTime = new TaskDateTime(due);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due: " + dueTime + ")";
    }

    @Override
    public String serialize() {
        return "D | " + super.serialize() + " | " + dueTime.serialize(); // assuming that the dueTime does not contain
                                                                         // "|"
    }
}
