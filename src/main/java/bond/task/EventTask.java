package bond.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bond.main.BondException;

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

        try {

            String[] startDateTime = start.split(" ");
            this.startDate = LocalDate.parse(startDateTime[0]);
            this.startTiming = startDateTime[1];

            String[] endDateTime = end.split(" ");
            this.endDate = LocalDate.parse(endDateTime[0]);
            this.endTiming = endDateTime[1];

        } catch (java.time.format.DateTimeParseException e) {
            BondException.raiseException("deadline", "INVALID_DATE_FORMAT");
        }

    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %s to: %s %s)", super.toString(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.startTiming,
                this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.endTiming);
    }
}
