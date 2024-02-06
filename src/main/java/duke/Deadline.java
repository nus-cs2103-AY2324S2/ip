package duke;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class
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
            this.byDate = parseDate(by, f1, f2, f3, f4); // Parse the by string
        } catch (DateTimeException e) {
            throw new DateTimeException("Error parsing date in Deadline: " + e.getMessage());
        }
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return super.toString() + " (by: " + byDate.format(displayFormatter) + ")";
    }
}