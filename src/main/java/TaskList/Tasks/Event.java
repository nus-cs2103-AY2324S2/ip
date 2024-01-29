package TaskList.Tasks;

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

    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.from) + " to: " + formatDateTime(this.to) + ")";
    }

    public String save() {
        return "event " + super.description + " /from " + formatDateTime(this.from) + " /to " + formatDateTime(this.to);
    }
}
