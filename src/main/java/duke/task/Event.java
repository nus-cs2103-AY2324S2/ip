package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
    private final static String OUTPUT_DATE_FORMAT = "yyyy-MM-dd" ;
    private final static String STORAGE_DATE_FORMAT = "MMM dd yyyy" ;
    protected String dateStart;
    protected String dateEnd;

    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        this.dateStart = LocalDate.parse(dateStart, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
        this.dateEnd = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return super.toString() + " Event : " + this.description + " [ " + this.dateStart +
                " - " + this.dateEnd + " ]";
    }
}
