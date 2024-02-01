package taskList.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime fromDateLDT;
    protected LocalDateTime toDateLDT;
    protected String fromDateString;
    protected String toDateString;

    public Event(String item, String fromDate, String toDate) {
        super(item);
        
        try {
            String fromDateString = fromDate.substring(fromDate.indexOf("/from") + 5, fromDate.length());
            String toDateString = toDate.substring(toDate.indexOf("/to") + 3, toDate.length());
            if (fromDateString.trim() == "" | toDateString.trim() == "") {
                throw new EmptyDateException();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            LocalDateTime fromDateLDT = LocalDateTime.parse(fromDateString.trim(),formatter);
            LocalDateTime toDateLDT = LocalDateTime.parse(toDateString.trim(),formatter);

            if (fromDateLDT.isAfter(toDateLDT)) {
                throw new IllegalArgumentException("Start date must be before end date");
            }

            this.fromDateLDT = fromDateLDT;
            this.fromDateString = fromDateString.trim();

            this.toDateLDT = toDateLDT;
            this.toDateString = toDateString.trim();

        } catch (DateTimeException dte) {
            throw new DateTimeParseException("Date format is incorrect, please try again", item, 0, dte);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()  + " (from: " + fromDateString + " to: " + toDateString + ")";
    }
}
