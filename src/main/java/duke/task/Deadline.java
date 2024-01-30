package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with deadline
 */
public class Deadline extends Task {
    //private final static Pattern OUTPUT_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); //In yyyy-MM-dd
    //private final static Pattern STORAGE_DATE_PATTERN = Pattern.compile("^\\w{3} \\d{2} \\d{4}$"); //In MMM dd yyyy
    private final static String OUTPUT_DATE_FORMAT = "yyyy-MM-dd" ;
    private final static String STORAGE_DATE_FORMAT = "MMM dd yyyy" ;

    protected String date;

    /**
     * Constructs a Deadline class with given parameters
     * @param description A string representation of task description
     * @param endDate A string representation of the deadline date in required format
     */
    public Deadline(String description, String endDate) {
        super(description);
        this.date = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
    }
    /**
     * Returns a string representation of a deadline task
     * @return string of deadline
     */
    @Override
    public String toString() {
        return super.toString() + " Deadline : " + this.description + " [ " + this.date + " ]";
    }
}
