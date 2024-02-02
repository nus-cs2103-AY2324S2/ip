package dave.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** The deadline of the task. */
    protected LocalDateTime deadline;
    /** The format of the input. */
    static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The format of the output. */
    static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    /**
     * Creates new Deadline object.
     * 
     * @param desc Name or description of Deadline object.
     * @param deadline Deadline of task.
     */
    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    /**
     * Formats the printing of the Deadline object when shown to user.
     * 
     * @return Printing of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[Deadline]%s (by: %s)", super.toString(), this.deadline.format(outputFormatter));
    }

    /**
     * Formats the output of the Deadline object in output file.
     * 
     * @return The output to be written in the output file.
     */
    @Override
    public String fileString() {
        return String.format("DEADLINE | %s | %s", super.fileString(), this.deadline.format(inputFormatter));
    }
}
