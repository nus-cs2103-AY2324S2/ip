package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A event task that includes a starting date and ending date
 */
public class Event extends Task {
    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String STORAGE_DATE_FORMAT = "MMM dd yyyy";
    protected String dateStart;
    protected String dateEnd;

    /**
     * Constructs an Event class with given parameters
     * @param description A string representation of task description
     * @param dateStart A string representation of the start date in required format
     * @param dateEnd A string representation of the end date in required format
     */
    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        this.dateStart = LocalDate.parse(dateStart, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT))
                .format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
        this.dateEnd = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT))
                .format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
    }

    /**
     * Returns a string representation of a event task
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " Event : " + this.description + " [ " + this.dateStart
                + " - " + this.dateEnd + " ]";
    }
}
