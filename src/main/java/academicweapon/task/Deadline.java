package academicweapon.task;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.parser.DateTimeParser;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline in the Duke application.
 * The Deadline class extends the Task class and includes a deadline specified by a date and time.
 */
public class Deadline extends Task {

    private String by;
    private LocalDateTime ldt = null;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the deadlline task
     * @param by The deadline specified by date and time
     * @throws DukeExceptions If there is an error in parsing the deadline format
     */
    public Deadline(String description, String by) throws DukeExceptions {
        super(description);
        this.by = by;
        try {
            ldt = DateTimeParser.parseDateTime(by);
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            throw new DukeExceptions("Please enter in the correct format.");
        }
    }

    /**
     * Returns a string representation of the Deadline task
     * Overrides the toString method in the Task class
     *
     * @return String representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.toString(this.ldt) + ")";
    }

    /**
     * Returns a formatted string suitable for storing the Deadline task in a file.
     * Overrides the fileToString method in the Task class.
     *
     * @return Formatted string for storing the Deadline task in a file.
     */
    @Override
    public String fileToString() {
        return "D | " + super.fileToString() + " | " + this.by;
    }
}
