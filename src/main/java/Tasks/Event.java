package Tasks;

import Exceptions.InvalidFormatException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public static Event EventOf(String input) throws InvalidFormatException {
        if (input.replaceAll(" ", "").equals("event")) {
            InvalidFormatException.callInvalidFormatException("event");
        }
        String[] t = input.replaceFirst("event ", "").split("/from ");
        if (t.length < 2) {
            InvalidFormatException.callInvalidFormatException("event");
        }
        String[] frTo = t[1].split("/to ");
        if (frTo.length < 2) {
            InvalidFormatException.callInvalidFormatException("event");
        }
        return new Event(t[0], frTo[0], frTo[1]);
    }
    @Override
    public String toString () {
        return String.format("[E]%s (From: %s To: %s)", super.toString(), this.from, this.to);
    }
}
