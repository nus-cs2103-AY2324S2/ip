package riri;

import java.time.LocalDate;
/**
 * This class represents the events in the task list.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event object representing a task with a specified time frame.
     *
     * This constructor creates an Event object with the given task description and the time
     * frame during which the event occurs. The 'from' and 'to' parameters are parsed into
     * LocalDate format to accurately represent the start and end dates of the event.
     *
     * @param task The task description provided by the user.
     * @param from The starting date and time of the event, provided in string format.
     *             The date is parsed internally to ensure accurate representation.
     *             The accepted date format is flexible, allowing natural language input.
     * @param to   The ending date and time of the event, provided in string format.
     *             The date is parsed internally to ensure accurate representation.
     *             The accepted date format is flexible, allowing natural language input.
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
     * Parses a formatted string to create an Event object.
     *
     * This static method is designed to create an Event object from a string formatted
     * in a specific way. The expected format is "[E][ ] task description (from: start_time to end_time)".
     * The method extracts relevant information such as task description, event start time, event end time,
     * and completion status to instantiate an Event object.
     *
     * @param inputString The formatted string to parse into an Event object.
     *                    It should follow the pattern "[E][ ] task description (from: start_time to end_time)".
     *
     * @return An Event object representing the parsed task with its completion status and event time frame.
     *
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
