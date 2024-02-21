package catchat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class handles the deadline tasks of the application
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byString;

    /**
     * Constructor for Deadline
     *
     * @param description
     * @param by
     * @throws DateTimeException
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;

        try {
            this.byDate = parseDate(by, DATE_FORMAT_1, DATE_FORMAT_2,
                    DATE_FORMAT_3, DATE_FORMAT_4); // Parse the by string
        } catch (DateTimeException e) {
            throw new DateTimeException("Error parsing date in Deadline: " + e.getMessage());
        }
    }

    @Override
    protected String getTaskTypeString() {
        return "D";
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DATE_FORMAT_4;
        return super.toString() + " (by: " + byDate.format(displayFormatter) + ")";
    }
}
