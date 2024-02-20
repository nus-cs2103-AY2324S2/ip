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
    protected LocalDate by;

    /**
     * A constructor for a Deadline object.
     * Used when 'by' is given as a String.
     *
     * @param description The description of the Deadline object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param by The deadline of the Deadline object, when given as a String object.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy"));
        } catch (DateTimeParseException ex) {
            System.out.println(ex);
        }
    }

    /**
     * A constructor for a Deadline object.
     * used when 'by' is given as a LocalDate.
     *
     * @param description The description of the Deadline object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param by The deadline of the Deadline object, when given as a LocalDate object.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }


    /**
     * Converts the Deadline object into a human-readable String to be displayed to the user.
     * @return String object that represents the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
