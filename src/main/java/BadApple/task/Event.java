package BadApple.task;

import BadApple.main.BadAppleException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A type of Task that has a start time and end time
 * It can also extract details to fill its relevant fields
 */
public class Event extends Task {
    protected String from;
    protected String to;

    Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Event" + " " +
                super.toString() +
                "(from: " + from +
                "to: " + to + ")";
    }
    /**
     * This is a Factory Method that generates an instance of Event
     * @param s an ArrayList after tokenizing the query by WhiteSpace.
     */
    public static Event extractDetails(ArrayList<String> s) {
        StringBuilder taskName = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");
        if (fromIndex > -1 && toIndex > -1) {
            for (int i = 1; i < fromIndex; i++) {
                taskName.append(s.get(i)).append(" ");
            }
            for (int i = fromIndex + 1; i < toIndex;i++) {
                from.append(s.get(i)).append(" ");
            }
            for (int i = toIndex + 1; i < s.size(); i++) {
                to.append(s.get(i)).append(" ");
            }
            return new Event(taskName.toString(), from.toString(), to.toString());
        } else {
            throw new BadAppleException("Usage: event TaskName /from time1 /to time2");
        }
    }
    /**
     * Creates a Command when a Task parsed from WHITESPACE is of
     * the Event type.
     * @param args Processed List of arguments
     * @return A generic Command which will be processed to add an Event when executed.
     */
    public static Command parseEventFromReader(ArrayList<String> args) {
        if (args.size() < 8 || !(args.contains("(from:") && args.contains("to:"))) {
            throw new BadAppleException("Event in wrong format" +
                    "should be <no.> 'event' <status> <description> " +
                    "'(from: ' <fromValue> 'to: ' <toValue>");
        }
        StringBuilder taskName = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        int fromSeparator = args.indexOf("(from:");
        for (int i = 3; i < fromSeparator; i++) {
            taskName.append(args.get(i)).append(" ");
        }
        int toSeparator = args.indexOf("to:");
        for (int i = fromSeparator + 1; i < toSeparator; i++) {
            from.append(args.get(i)).append(" ");
        }
        for (int i = toSeparator + 1; i < args.size() - 1; i++) {
            to.append(args.get(i)).append(" ");
        }
        String query = "event " + taskName + "/from " + from + "/to " + to;

        return new Command("event", query);
    }

    // in case anyone tries to throw an un-formatted string, the program still runs
    public static Event extractDetails(String s) {
        return extractDetails(new ArrayList<>(Arrays.asList(s.split(" "))));
    }
}
