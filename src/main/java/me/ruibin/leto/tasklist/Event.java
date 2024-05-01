package me.ruibin.leto.tasklist;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A type of Task with LocalDate fields specifying from date and to date. */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    private Event(Boolean completed, String taskString, LocalDate from, LocalDate to) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, taskString);
        this.from = from;
        this.to = to;
    }

    private String eventDaysMessage() {
        long daysToStart = ChronoUnit.DAYS.between(LocalDate.now(), this.from);
        long daysToEnd = ChronoUnit.DAYS.between(LocalDate.now(), this.to);
        if (daysToStart > 0) {
            return Math.abs(daysToStart) + " days to the event";
        } else if (daysToEnd < 0) {
            return "ended " + Math.abs(daysToEnd) + " days ago";
        } else {
            return daysToEnd + " days remaining";
        }
    }

    /**
     * Create a new instance of Event from user command.
     *
     * @param input Command inputs from user.
     * @return A new instance of Event.
     * @throws InvalidTaskException If input is not a valid event command.
     */
    public static Event eventFromCommand(String input) throws InvalidTaskException {
        String regex = "(?i)event ([^,]+) /from (\\d{4}-\\d{2}-\\d{2}) /to (\\d{4}-\\d{2}-\\d{2}) *";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches()) {
            throw new EventInvalidCmdException();
        }
        String taskMessage = matcher.group(1).trim();
        LocalDate from = LocalDate.parse(matcher.group(2).trim());
        LocalDate to = LocalDate.parse(matcher.group(3).trim());
        return new Event(false, taskMessage, from, to);
    }

    /**
     * Create an Event task from csv format:<br>
     * <code>Type,Completed,Task,By,From,To</code>.
     *
     * @param entry text string containing the row in the csv
     * @return an Event task
     */
    public static Event eventFromCsv(String entry) throws InvalidTaskException {
        String regex = "([DTE]),([YN]),([^,]*),([^,]*),(\\d{4}-\\d{2}-\\d{2}),(\\d{4}-\\d{2}-\\d{2})(\\n?)";
        Matcher matcher = Pattern.compile(regex).matcher(entry);
        if (!matcher.matches()) {
            throw new InvalidTaskException("Cannot match " + entry + " with regex");
        }
        Boolean completed = matcher.group(2).equals("Y");
        String message = matcher.group(3);
        LocalDate from = LocalDate.parse(matcher.group(5));
        LocalDate to = LocalDate.parse(matcher.group(6));
        return new Event(completed, message, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")"
                + " " + this.eventDaysMessage();
    }

    /**
     * Returns the object as a row in a csv table according to format:<br>
     * <code>Type,Completed,Task,By,From,To</code>.
     *
     * @return String in csv format
     */
    public String toCsvString() {
        return "E," + super.toCsvString() + ",," + this.from + "," + this.to;
    }
}
