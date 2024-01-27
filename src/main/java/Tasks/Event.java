package Tasks;

import Exceptions.InvalidInputException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.StringUtils.formatDateTime;
import static Utils.StringUtils.parseDateTime;

public class Event extends Task{
    LocalDateTime from;
    LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event EventFactory(String description) throws InvalidInputException {
        try {
            String regex = "event\\s+(.+?)\\s*/from\\s+(.+?)\\s*/to\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(description);
            if (matcher.find()) {
                String eventName = matcher.group(1);
                return new Event(eventName, parseDateTime(matcher.group(2)), parseDateTime(matcher.group(3)));
            } else {
                throw new InvalidInputException("Invalid input for event. Input your event as such:\nevent <name_of_event> /from <start_time> /to <end_time>");
            }
        } catch (Exception e){
            throw new InvalidInputException("Invalid input for event. Input your event as such:\nevent <name_of_event> /from <start_time> /to <end_time>");
        }
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.from) + " to: " + formatDateTime(this.to) + ")";
    }

    public String save() {
        return "event " + super.description + " /from " + formatDateTime(this.from) + " /to " + formatDateTime(this.to);
    }
}
