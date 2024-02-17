package fluffy.task;

import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;
    /**
     * Constructor for Deadline.
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     * @param isDone Whether the deadline is done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the deadline in the format to be displayed to the user.
     * @return The deadline in the format to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (by: " + by.format(Task.DATE_TIME_FORMATTER) + ")";
    }

    /**
     * Returns the deadline in the format to be saved in the file.
     * @return The deadline in the format to be saved in the file.
     */
    @Override
    public String toFileString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Factory method to create a Deadline object from a file string.
     * @param fileString The file string to be converted.
     * @return The Deadline object from the file string.
     */
    public static Deadline deadlineFromFileString(String fileString) {
        String[] taskDetails = fileString.split(" \\| ");
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        LocalDate by = LocalDate.parse(taskDetails[3]);
        return new Deadline(description, by, isDone);
    }
}
