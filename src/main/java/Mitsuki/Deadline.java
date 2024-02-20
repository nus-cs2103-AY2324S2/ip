package Mitsuki;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains information for the creation of Deadline objects.
 *
 * @author Tee Chu Jie
 */
public class Deadline extends Task {
    /**
     * The deadline of the Deadline object to be created.
     */
    protected LocalDate deadline;

    /**
     * A constructor for a Deadline object.
     * Used when 'deadline' is given as a String.
     *
     * @param description The description of the Deadline object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param deadline The deadline of the Deadline object, when given as a String object.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("d/MM/yyyy"));
        } catch (DateTimeParseException ex) {
            System.out.println(ex);
        }
    }

    /**
     * A constructor for a Deadline object.
     * used when 'deadline' is given as a LocalDate.
     *
     * @param description The description of the Deadline object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param deadline The deadline of the Deadline object, when given as a LocalDate object.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }


    /**
     * Converts the Deadline object into a human-readable String to be displayed to the user.
     * @return String object that represents the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
