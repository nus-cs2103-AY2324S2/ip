package ellie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/* Events: tasks that start at a specific date/time and
 * ends at a specific date/time e.g.,
 * (a) event team project meeting /from 2-10-2019 /to 2-4pm
 * (b) event orientation week /from 4-10-2019 /to 11-10-2019
 * */
public class Event extends Task {

    private String startDateString, endDateString;

    private LocalDate startDate, endDate;

    

    public Event(String description, String startDateString, String endDateString) {
        super(description);
        this.startDateString = startDateString;
        this.endDateString = endDateString;

        try {
            startDate = LocalDate.parse(startDateString);
            endDate = LocalDate.parse(endDateString);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }

    }

    public Event(String description, int isDone, String startDateString, String endDateString) {
        super(description, isDone);
        this.startDateString = startDateString;
        this.endDateString = endDateString;

        try {
            startDate = LocalDate.parse(startDateString);
            endDate = LocalDate.parse(endDateString);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }
    }

    @Override
    public char getTaskType() {
        return 'E';
    }

    // project meeting (from: Aug 6th 2pm to: 4pm)
    @Override
    public String listTaskString() {
        if (startDate == null) {
            return "[E]" + super.listTaskString()
                    + " (from: " + startDateString
                    + " to: " + endDateString + ")";
        } else {
            return "[E]" + super.listTaskString()
                    + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    public String getStartDate() {
        return this.startDateString;
    }

    public String getEndDate() {
        return this.endDateString;
    }


}
