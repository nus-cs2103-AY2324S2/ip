package Tasks;

import Exceptions.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task{
    String from;
    String to;
    public Event(String description, String from, String to) {
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
                String startTime = matcher.group(2);
                String endTime = matcher.group(3);
                return new Event(eventName, startTime, endTime);
            } else {
                throw new InvalidInputException("Invalid input for event. Input your event as such:\nevent <name_of_event> /from <start_time> /to <end_time>");
            }
        } catch (Exception e){
            throw new InvalidInputException("Invalid input for event. Input your event as such:\nevent <name_of_event> /from <start_time> /to <end_time>");
        }
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
