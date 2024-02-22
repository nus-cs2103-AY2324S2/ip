package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static Ui ui = new Ui();

    /**
     * Constructs a new Event object.
     *
     * @param description The description of the task.
     * @param from        The start time of the task, in the format "dd/MM/yyyy
     *                    HHmm".
     * @param to          The end time of the task, in the format "dd/MM/yyyy HHmm".
     * @throws DukeException If the start or end time is not in the correct format.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.to = LocalDateTime.parse(to,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Invalid date format. Please use dd/MM/yyyy HHmm format. Example: 02/12/2019 1800\n");
        }
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + " | "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    /**
     * Creates a new Event object from a string.
     *
     * @param input The string to create the Event object from.
     * @return A new Event object, or null if the Event object could not be created.
     */
    public static Event fromString(String input) {
        String[] split = input.split(" \\| ");
        try {
            Event event = new Event(split[2], split[3], split[4]);
            if (split[1].equals("X")) {
                event.markAsDone();
            }
            return event;
        } catch (DukeException e) {
            ui.printMessage("Error creating event");
            return null;
        }
    }

    /**
     * Returns a string representation of the task that can be written to a file.
     *
     * @return A string representation of the task that can be written to a file.
     */
    public String toFileString() {
        return "E | " + super.toString() + " | "
                + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + " | "
                + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public static String getHelpString() {
        return "Add Event: Adds an event to the task list.\n"
                + "  To add an event, use the following format:\n"
                + "    event <description> /at <start date> <start time> <end date> <end time>\n"
                + "  The date and time should be in the format dd/MM/yyyy HHmm.\n"
                + "    Example: event student meeting /at 02/12/2019 1800 02/12/2019 2000\n";
    }
}