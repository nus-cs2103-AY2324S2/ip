package duke;

import java.time.LocalDateTime;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     * @param description Description of the deadline.
     * @param deadline Deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public boolean isClashingWith(Task otherTask) {
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(Constants.OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "deadline " + description + " /by " + deadline.format(Constants.INPUT_FORMATTER) + "\n"
            + serializeDoneMark(taskIndex);
    }
}
