package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String from;
    String to;
    LocalDate fromDate;
    LocalDate toDate;

    public Event(String task, String from, String to) {
        super(task);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
        this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.fromDate = from;
        this.toDate = to;
        this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

    /** Static method to create an Event object from a formatted string
     * @param inputString string to parse to Event object
     * @return Event object
     */
    public static Event parseEventFromString(String inputString) {
        // Assuming the inputString is formatted as "[ ][ ] task description (from: start_time to end_time)"
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
