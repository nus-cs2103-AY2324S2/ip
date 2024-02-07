package tasks;

import exceptions.InvalidFormatException;
import exceptions.LeluException;

/**
 * This class represents an Event task by encapsulating information about a specific event,
 * including the description, start and end timings.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * A factory method which creates an Event object using the user input.
     *
     * @param input User input which starts with "event".
     * @return An Event object with the specified name and start and end timings.
     * @throws InvalidFormatException When the description of event, or start and end times, is empty in the user input.
     */
    public static Event EventOf(String input) throws InvalidFormatException {
        if (input.replaceAll(" ", "").equals("event")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.EVENT);
        }
        String[] t = input.replaceFirst("event ", "").split("/from ");
        if (t.length < 2) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.EVENT);
        }
        String[] frTo = t[1].split("/to ");
        if (frTo.length < 2) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.EVENT);
        }
        return new Event(t[0], frTo[0], frTo[1]);
    }

    /**
     * Formats the details of the Event object as a String to be written to a text file.
     *
     * @return A String containing the description and start and end timings of an Event object.
     */
    @Override
    public String saveFormat() {
        int check = this.isCompleted ? 1 : 0;
        return String.format("E | %d | %s | %s | %s\n", check, this.taskName, this.from, this.to);
    }
    @Override
    public String toString () {
        return String.format("[E]%s (From: %s To: %s)", super.toString(), this.from, this.to);
    }
}
