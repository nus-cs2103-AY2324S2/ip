package MissMinutes;

import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

public class Event extends Task implements Serializable {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private static final Pattern formatter = Pattern.compile("(.+) /from (.+) /to (.+)");

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(Task.outputFormat)
                + " to: " + endTime.format(Task.outputFormat) + ")";
    }
}
