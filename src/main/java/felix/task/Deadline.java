package felix.task;

import java.time.LocalDateTime;

/**
 * Class representing tasks with an end date
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of task.
     * @param deadline Date and time by which the task be completed.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    /**
     * Returns the String representation of the Deadline instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(),
                this.getDescription(), this.deadline.format(INPUT_FORMATTER));
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(OUTPUT_FORMATTER));
    }
}
