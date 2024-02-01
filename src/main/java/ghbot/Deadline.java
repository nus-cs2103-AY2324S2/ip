package ghbot;

/**
 * Deadline class.
 * This class contains informations about the deadline.
 */
public class Deadline extends Task {
    private String time;

    /**
     * ghbot.Deadline Constructor.
     * @param description Description of the event.
     * @param time ghbot.Deadline for the task.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Return a string description for the item in the list for file operation.
     * @return a string.
     */
    @Override
    public String toFile() {
        return "D " + super.toFile() + " | " + this.time.trim();
    }

    /**
     * Return a string description of a deadline task.
     * @return a string that describe the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.trim() + ")";
    }
}

