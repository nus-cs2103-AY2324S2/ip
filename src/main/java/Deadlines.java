/**
 * Deadlines - Represents a task with a deadline, a subclass of Task.
 */

public class Deadlines extends Task {
    private String byDate;
    public Deadlines(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}