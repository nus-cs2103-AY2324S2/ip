package huyang;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a specified deadline time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param taskName The name of the deadline task.
     * @param by       The deadline time for the task.
     */
    public Deadline(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    /**
     * Gets the deadline time for the task.
     *
     * @return The deadline time as a LocalDateTime object.
     */
    public LocalDateTime getTime() {
        return by;
    }

    /**
     * Converts the deadline task to a formatted string for saving to a file.
     *
     * @return A string in file format representing the deadline task.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return "D | " + (isDone ? "1" : "0") + " | " + taskName + " | " + by.format(formatter);
    }

    /**
     * Creates a Deadline object from a string in file format.
     *
     * @param fileFormat A string in file format representing a deadline task.
     * @return A Deadline object created from the file format string.
     * @throws TaskException if the file format is invalid.
     */
    public static Deadline fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        LocalDateTime byTime = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Deadline deadline = new Deadline(parts[2], byTime);
        if (parts[1].equals("1")) {
            deadline.setDone(true);
        }
        return deadline;
    }

    /**
     * Converts the deadline task to a formatted string for displaying to the user.
     *
     * @return A string representing the formatted deadline task.
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
