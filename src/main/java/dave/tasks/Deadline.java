package dave.tasks;

import dave.utils.DateTimeFormat;

import java.time.LocalDateTime;

public class Deadline extends Task {
    /** The deadline of the task. */
    protected LocalDateTime deadline;

    /**
     * Creates new Deadline object.
     * 
     * @param desc Name or description of Deadline object.
     * @param deadline Deadline of task.
     */
    public Deadline(String desc, String deadlineInput) {
        super(desc);
        deadline = LocalDateTime.parse(deadlineInput, DateTimeFormat.FORMATTER_INPUT);
    }

    /**
     * Formats the printing of the Deadline object when shown to user.
     * 
     * @return Printing of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[Deadline]%s (by: %s)", super.toString(), deadline.format(DateTimeFormat.FORMATTER_OUTPUT));
    }

    /**
     * Formats the output of the Deadline object in output file.
     * 
     * @return The output to be written in the output file.
     */
    @Override
    public String fileString() {
        return String.format("DEADLINE | %s | %s", super.fileString(), deadline.format(DateTimeFormat.FORMATTER_INPUT));
    }
}
