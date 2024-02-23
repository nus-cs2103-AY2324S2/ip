package bozo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, input);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return string of deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(output));
    }

    /**
     * Returns the string representation of the deadline task to be saved in the file.
     *
     * @return string of deadline task
     */
    @Override
    public String save() {
        return "D " + super.save() + String.format(" | %s", by.format(input));
    }
}
