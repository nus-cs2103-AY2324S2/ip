package bytebuddy.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

/**
 * The Deadline class represents a task with a specified deadline.
 * It extends the Task class and includes additional properties for the deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected Optional<LocalDate> byDate;
    protected Optional<LocalDateTime> byDateTime;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by           The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        this.byDate = parseDate(by);
        this.byDateTime = parseDateTime(by);

        // Initialise 'by' string depending on type, else use given 'by' string as is
        if (byDateTime.isPresent() || byDate.isPresent()) {
            this.by = formatByString(by);
        } else {
            this.by = by;
        }
    }

    /**
     * Constructs a new Deadline task with completion status, description, and deadline.
     *
     * @param completed   The completion status of the task (1 for done, 0 for not done).
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String completed, String description, String by) {
        super(description, completed.equals("1"));
        this.by = by;
    }

    /**
     * Parses the date and time from the input string.
     *
     * @param by The input string containing date and time information.
     * @return An Optional containing LocalDateTime if parsing is successful, empty otherwise.
     */
    private Optional<LocalDateTime> parseDateTime(String by) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return Optional.of(LocalDateTime.parse(by, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Parses the date from the input string.
     *
     * @param by The input string containing date information.
     * @return An Optional containing LocalDate if parsing is successful, empty otherwise.
     */
    private Optional<LocalDate> parseDate(String by) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Optional.of(LocalDate.parse(by, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Formats the 'by' string based on the type of date or date-time.
     *
     * @param by The default string representation if no special formatting is required.
     * @return The formatted 'by' string.
     */
    private String formatByString(String by) {
        if (byDateTime.isPresent()) {
            return byDateTime.get().format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha"));
        } else if (byDate.isPresent()) {
            return byDate.get().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return by;
    }

    /**
     * Returns a formatted string representation of the Deadline task for writing into output file.
     *
     * @return The formatted output string.
     */
    @Override
    public String getTextFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", intIsDone, description, by);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method considers two Deadline objects equal if they have the same description, completion status,
     * and deadline.
     *
     * @param obj the reference object with which to compare.
     * @return true if this Deadline is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return isDone == deadline.isDone
                && Objects.equals(description, deadline.description)
                && Objects.equals(by, deadline.by);
    }

    /**
     * Returns a hash code value for the Deadline object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, by);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
