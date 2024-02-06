import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter INPUT_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Command parse(String input) throws LivException {
        if (input.startsWith("event")) {
            parseEventCommand(input);
        }
        return null;
    }
    public Command parseEventCommand(String input) throws LivException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        int timeIntervalIndex = input.indexOf('/');
        if (timeIntervalIndex == -1) {
            throw new LivException("Time cannot be empty!");
        }
        String description = input.substring(spaceIndex + 1, timeIntervalIndex - 1);
        String timeInterval = input.substring(timeIntervalIndex + 6);
        int splitterIndex = timeInterval.indexOf('/');
        if (splitterIndex == -1) {
            throw new LivException("Please enter the correct format for time!");
        }
        String time1 = timeInterval.substring(0, splitterIndex - 1);
        String time2 = timeInterval.substring(splitterIndex + 4);
        LocalDateTime from = LocalDateTime.parse(time1, INPUT_PATTERN);
        LocalDateTime to = LocalDateTime.parse(time2, INPUT_PATTERN);
        Event newEvent = new Event(description, from, to);
        return new EventCommand(newEvent);
    }
}
