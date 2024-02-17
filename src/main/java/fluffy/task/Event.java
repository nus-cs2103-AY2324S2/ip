package fluffy.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import fluffy.FluffyException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event.
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws FluffyException If the date is not in the correct format.
     */
    public Event(String description, String from, String to) throws FluffyException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new FluffyException("Please enter a valid date in the format yyyy-mm-dd");
        }
    }

    /**
     * Constructor for Event.
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event.
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @param isDone Whether the event is done.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the event in the format to be displayed to the user.
     * @return The event in the format to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (from: " + from.format(Task.DATE_TIME_FORMATTER)
                + " to: " + to.format(Task.DATE_TIME_FORMATTER) + ")";
    }

    /**
     * Returns the event in the format to be saved in the file.
     * @return The event in the format to be saved in the file.
     */
    @Override
    public String toFileString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Factory method to create an event from a file string.
     * @param fileString The file string to be converted to an event.
     * @return The event from the file string.
     */
    public static Event eventFromFileString(String fileString) {
        String[] taskDetails = fileString.split(" \\| ");
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        LocalDate from = LocalDate.parse(taskDetails[3]);
        LocalDate to = LocalDate.parse(taskDetails[4]);
        return new Event(description, from, to, isDone);
    }
}
