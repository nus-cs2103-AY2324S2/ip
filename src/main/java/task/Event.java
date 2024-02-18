/**
 * This is an Event Task.
 * It is a task that contains a start and an end date.
 */

package task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDateTime start;
    protected LocalDateTime end;


    public Event(String description, String from, String to) throws InvalidInputException, InvalidDateException {
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a event cannot be empty.");
        }
        else if(from.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The start time of a event cannot be empty.");
        }
        else if (to.isEmpty()){
            throw new InvalidInputException("OOPS!!! The end time of a event cannot be empty.");
        }
        else {
            this.from = cleanWhiteSpace(from);
            this.to = cleanWhiteSpace(to);
            this.start = parseDate(this.from);
            this.end = parseDate(this.to);
        }
    }

    /**
     * Parses the date given by the user from a string format to
     * a LocalDateTime format.
     *
     * @param date the date provided by the user
     * @return the LocalDateTime format of the date provided
     * @throws InvalidDateException if the date provided cannot be interpreted by this method
     */
    public LocalDateTime parseDate(String date) throws InvalidDateException {
        String[] brokenStart = date.split("[\\s/-]+");

        try {
            int year = Integer.parseInt(brokenStart[2]);
            int month = Integer.parseInt(brokenStart[1]);
            int day = Integer.parseInt(brokenStart[0]);;
            int hour = Integer.parseInt(brokenStart[3].substring(0, 2));
            int minute = Integer.parseInt(brokenStart[3].substring(2));

            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new InvalidDateException("Start/end date provided is not valid");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
