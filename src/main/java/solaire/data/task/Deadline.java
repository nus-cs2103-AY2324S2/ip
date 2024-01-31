package solaire.data.task;

import solaire.data.exception.SolaireException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a task with a specified deadline.
     *
     * @param taskName description of the task.
     * @param ddl deadline as a String.
     * @throws SolaireException if ddl does not follow the LocalDate format specified.
     */
    public Deadline(String taskName, String ddl) throws SolaireException {
        super(taskName);
        this.by = parseDeadline(ddl);
    }

    private LocalDate parseDeadline(String ddl) throws SolaireException {
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(ddl, f);
        } catch (DateTimeParseException e) {
            throw new SolaireException("Deadline must be in the format: yyyy-mm-dd");
        }
    }

    public LocalDate getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        String formattedDate = this.by.getDayOfMonth() + " " + this.by.getMonth().toString().substring(0, 3) + " "
                + this.by.getYear();
        return "[D]" + super.toString() + " ( by: " + formattedDate + ")";
    }
}
