package duke;

import java.time.LocalDateTime;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Constants.INPUT_FORMATTER);
    }

    @Override
    public boolean isClashingWith(Task otherTask) {
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Constants.OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "deadline " + description + " /by " + by.format(Constants.INPUT_FORMATTER) + "\n"
            + serializeDoneMark(taskIndex);
    }
}
