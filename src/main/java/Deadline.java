public class Deadline extends Task {
    private String by;
    Deadline(String text, TaskStatus status, String by) {
        super(text, status);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by  + " )";
    }
}
