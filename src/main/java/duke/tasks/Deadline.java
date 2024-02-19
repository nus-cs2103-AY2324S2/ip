package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Constructor for the deadline class
     *
     * @param description Description of the deadline.
     * @param by End date for the deadline.
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description, 'D');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dueDateParsed = LocalDateTime.parse(by.trim(), formatter);
        this.dueDate = dueDateParsed;
    }

    /**
     * Convert by date from LocalDateTime to String
     *
     * @return String By date of the deadline.
     */
    public String dateInWords() {
        String dayWeek = dueDate.getDayOfWeek().toString();
        int dayMonth = dueDate.getDayOfMonth();
        String month = dueDate.getMonth().toString();
        int year = dueDate.getYear();
        return dayWeek + " " + dayMonth + " " + month + " " + year;
    }

    @Override
    public String toString() {
        String str = String.format(
                super.toString() + " (%s)", this.dateInWords());
        return str;
    }
}
