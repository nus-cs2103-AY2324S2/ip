package solaire.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import solaire.data.exception.SolaireException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a task with a specified deadline.
     *
     * @param taskName description of the task.
     * @param ddl      deadline as a String.
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

    /**
     * Returns the due status of the task based on the current date and time.
     *
     * @param buffer the number of days before the deadline to check for due status.
     * @return true if the task is due within the buffer, false otherwise.
     */
    public boolean getDueStatus(int buffer) {
        LocalDate currentDate = LocalDate.now();
        LocalDate limit = currentDate.plusDays(buffer);
        boolean isOverdue = this.by.isBefore(currentDate);
        if (!isOverdue && (this.by.isBefore(limit) || this.by.isEqual(limit))) {
            return true;
        } else {
            return false;
        }
    }
}
