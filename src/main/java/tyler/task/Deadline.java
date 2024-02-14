package tyler.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. Have an extra argument called end to store the deadline.
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    protected LocalDateTime end;

    /**
     * Constructor of Deadline Task
     *
     * @param name Name of Task
     * @param end  Deadline of Task
     */
    public Deadline(String name, LocalDateTime end) {
        super(name);
        this.end = end;
    }

    /**
     * Constructor of Deadline Task
     *
     * @param name   Name of Task
     * @param end    Deadline of Task
     * @param isDone Status of Task
     */
    public Deadline(String name, LocalDateTime end, boolean isDone) {
        super(name);
        this.end = end;
        this.isDone = isDone;
    }

    /**
     * This method is used by storage when the storage need to save file to local.
     *
     * @return String representation of this task to be saved to the local.
     */
    @Override
    public String saveToFileString() {
        return "D | " + super.saveToFileString() + " | " + this.end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[D]" + str + " (by: " + this.end.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
