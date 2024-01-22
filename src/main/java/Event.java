import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private final String startTime;
    private final String endTime;
    private static final Pattern formatter = Pattern.compile("(.+) /from (.+) /to (.+)");

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event fromStr(String input) {
        Matcher matcher = Event.formatter.matcher(input);

        if (matcher.find()) {
            String name = matcher.group(1);
            String startTime = matcher.group(2);
            String endTime = matcher.group(3);

            return new Event(name, startTime, endTime);
        }
        return null; // should throw an error instead
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
