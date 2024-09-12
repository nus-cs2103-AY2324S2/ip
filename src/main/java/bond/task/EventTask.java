package bond.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bond.main.BondException;
import bond.main.Parser;

/**
 * Represents an event task in the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class EventTask extends Task {

    private LocalDate startDate;
    private String startTiming;
    private LocalDate endDate;
    private String endTiming;

    /**
     * Constructor for the EventTask class.
     * Throws a BondException if the start and/or end datetime is in an incorrect
     * format.
     *
     * @param name  The name of the event task.
     * @param start The start date and time of the event.
     * @param end   The end date and time of the event.
     * @throws BondException If the event task cannot be created.
     */
    public EventTask(String name, String start, String end) throws BondException {
        super(name);
        setStartDatetime(start);
        setEndDatetime(end);
    }

    public void setStartDatetime(String start) throws BondException {
        String[] startDateTime = start.split(" ");

        if (startDateTime.length != 2) {
            BondException.raiseException("event", "INVALID_DATETIME_FORMAT");
        }

        try {
            this.startDate = LocalDate.parse(startDateTime[0]);
        } catch (java.time.format.DateTimeParseException e) {
            BondException.raiseException("deadline", "INVALID_DATE_FORMAT");
        }

        if (!Parser.isValidTiming(startDateTime[1])) {
            BondException.raiseException("event", "INVALID_TIME_FORMAT");
        }

        this.startTiming = Parser.changeTimeFormat(startDateTime[1]);
    }

    public void setEndDatetime(String end) throws BondException {
        String[] endDateTime = end.split(" ");

        if (endDateTime.length != 2) {
            BondException.raiseException("event", "INVALID_DATETIME_FORMAT");
        }

        try {
            this.endDate = LocalDate.parse(endDateTime[0]);
        } catch (java.time.format.DateTimeParseException e) {
            BondException.raiseException("deadline", "INVALID_DATE_FORMAT");
        }

        if (!Parser.isValidTiming(endDateTime[1])) {
            BondException.raiseException("event", "INVALID_TIME_FORMAT");
        }

        this.endTiming = Parser.changeTimeFormat(endDateTime[1]);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %s to: %s %s)", super.toString(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.startTiming,
                this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.endTiming);
    }
}
