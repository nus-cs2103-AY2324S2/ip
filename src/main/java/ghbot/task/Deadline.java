package ghbot.task;

/**
 * Deadline Class.
 * This class contains information about the deadline.
 */
public class Deadline extends Task {
    private String time;

    /**
     * Deadline Constructor.
     * @param description Description of the event.
     * @param time Deadline for the task.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string description for the item in the list for file operation.
     * @return A string that describes the item in the list for file operation.
     */
    @Override
    public String toFile() {
        return "D " + super.toFile() + " | " + this.time.trim();
    }

    /**
     * Returns a string description of a deadline task.
     * @return A string that describes the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.trim() + ")";
    }
}

