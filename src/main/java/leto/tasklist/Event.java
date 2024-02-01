package leto.tasklist;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    private Event(Boolean completed, String TaskString, LocalDate from, LocalDate to) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, TaskString);
        this.from = from;
        this.to = to;
    }


    public static Event EventFactory(String message) throws InvalidTaskException {
        String regex = "event ([^,]+) /from (\\d{4}-\\d{2}-\\d{2}) /to (\\d{4}-\\d{2}-\\d{2})";
        Matcher matcher = Pattern.compile(regex).matcher(message);
        if (!matcher.matches()) {
            throw new EventInvalidCmdException();
        }
        String taskMessage = matcher.group(1).trim();
        LocalDate from = LocalDate.parse(matcher.group(2).trim());
        LocalDate to = LocalDate.parse(matcher.group(3).trim());
        return new Event(false, taskMessage, from, to);
    }

    /**
     * Create an Event task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return an Event task
     */
    public static Event EventFromCSV(String entry) throws InvalidTaskException {
        String regex = "([DTE]),([YN]),([^,]*),([^,]*),([^,]*),([^,]*)(\\n?)";
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
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to +")";
    }

    /**
     * Returns the object as a row in a csv table according to format
     * Type,Completed,Task,By,From,To
     * @return String in csv format
     */
    public String toCSVString() {
        return "E," + super.toCSVString() + ",," + this.from + "," + this.to;
    }
}
