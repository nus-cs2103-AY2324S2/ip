package duke;

import java.time.LocalDate;
/**
 * This class represents the events in the task list.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates events. String is parsed into LocalDate format.
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = super.parseDate(from);
        this.to = super.parseDate(to);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + super.stringifyDate(from) + " to " + super.stringifyDate(to) + ")";
    }
    /**
     * Static method to create an Event object from a formatted string
     * @param inputString string to parse to an Event object
     * @return Event object
     */
    public static Event parseEventFromString(String inputString) {
        // Assuming the inputString is formatted as "[E][ ] task description (from: start_time to end_time)"
        int indexOfFirstBracket = inputString.indexOf('[');
        int indexOfSecondBracket = inputString.indexOf(']');
        int indexOfFrom = inputString.indexOf("(from:");
        int indexOfTo = inputString.indexOf("to");

        char status = inputString.charAt(4);
        String taskDescription = inputString.substring(indexOfSecondBracket + 4, indexOfFrom).trim();
        String from = inputString.substring(indexOfFrom + 6, indexOfTo).trim();
        String to = inputString.substring(indexOfTo + 2, inputString.length() - 1).trim();

        Event event = new Event(taskDescription, from, to);

        // Check the status and mark the Event as done if needed
        if (status == 'X') {
            event.markDone();
        }

        return event;
    }
}
