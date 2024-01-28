public class Deadline extends Task {
    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "deadline " + description + " /by " + by + "\n" + serializeDoneMark(taskIndex);
    }
}
