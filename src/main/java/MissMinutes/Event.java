package MissMinutes;

import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

/**
 * Represents a specialised task called Event with a start time and end time
 *
 * @implements Serializable to be stored in binary format files for persistence
 */
public class Event extends Task implements Serializable {
    protected final LocalDateTime startTime;
    protected final LocalDateTime endTime;
    protected static final Pattern formatter = Pattern.compile("(.+) /from (.+) /to (.+)");

    /**
     * Creates a Event object with a given name, start time and end time
     * @param name The name of the Event
     * @param startTime The start time of the Event
     * @param endTime The end time of the Event
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a new Event object from the user input (string)
     *
     * @param input The string representation of the Event object
     * @return The Event object deserialized from the string
     * @throws MissMinutesException If the given string is in incorrect format
     */
    public static Event fromStr(String input) throws MissMinutesException {
        Matcher matcher = Event.formatter.matcher(input);

        if (matcher.find()) {
            String name = matcher.group(1);
            String startTimeStr = matcher.group(2);
            String endTimeStr = matcher.group(3);

            LocalDateTime startTime, endTime;
            try {
                startTime = LocalDateTime.parse(startTimeStr, Task.inputFormat);
                endTime = LocalDateTime.parse(endTimeStr, Task.inputFormat);
            } catch (DateTimeParseException err) {
                throw new MissMinutesException("Please enter a valid date time format. For example, 2019-12-31 1800");
            }

            return new Event(name, startTime, endTime);
        } else {
            throw new MissMinutesException("Events have to be created with the following format: event <desc> /from <start> /to <end>");
        }
    }

    /**
     * Returns the string representation of the Event object to be printed
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(Task.outputFormat)
                + " to: " + endTime.format(Task.outputFormat) + ")";
    }
}
