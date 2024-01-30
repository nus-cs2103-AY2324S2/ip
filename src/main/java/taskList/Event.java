package taskList;

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
            String[] fromDateStringArr = fromDate.split(" ", 2);
            //System.out.println(dateString[1].trim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            LocalDateTime fromDateLDT = LocalDateTime.parse(fromDateStringArr[1].trim(),formatter);
            String[] toDateStringArr = toDate.split(" ", 2);
            LocalDateTime toDateLDT = LocalDateTime.parse(toDateStringArr[1].trim(),formatter);

            if (fromDateLDT.isAfter(toDateLDT)) {
                throw new IllegalArgumentException("Start date must be before end date");
            }

            this.fromDateLDT = fromDateLDT;
            this.fromDateString = fromDateStringArr[1].trim();

            this.toDateLDT = toDateLDT;
            this.toDateString = toDateStringArr[1].trim();
  
         } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a date");
        } catch (DateTimeParseException dte) {
            throw new DateTimeParseException("Date format is incorrect, please try again", item, 0, dte);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()  + " (from: " + fromDateString + " to: " + toDateString + ")";
    }
}
