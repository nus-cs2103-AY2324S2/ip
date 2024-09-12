package duchess.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Create new Deadline object.
     *
     * @param description description of Deadline Task.
     * @param by deadline of Deadline Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns String representation of object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
