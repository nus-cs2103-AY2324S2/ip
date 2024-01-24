public class Deadline extends Task {
    private String due;
    public Deadline(String taskName, String due) {
        super(taskName);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format(" (by: %s)", due);
    }
}
