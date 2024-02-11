package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * This class represents an Event task.
 * It extends the Task class with a specific date range and a specific string representation.
 */
public class Event extends Task {
    private LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter NEW_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a new Event task with the specified name and date range.
     *
     * @param name the name of the Event task
     * @param from the start date of the event
     * @param to the end date of the event
     * @throws InvalidDateFormatException if the date format is invalid
     */
    public Event(String name, String from, String to) throws InvalidDateFormatException {
        super(name.trim());
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Returns a string representation of the Event task.
     * The returned string includes the task type ([E]), the string representation of the superclass, and the date range.
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        String date = String.format(" (from: %s to: %s)", this.from.format(FORMATTER), this.to.format(FORMATTER));
        return "[E]" + super.toString() + date;
    }

    /**
     * Returns the encoded version of Event
     *
     * @return Encoded String version of the Event. Currently, uses toString() to encode.
     */
    public String encode() {
        return toString();
    }

    /**
     * Decodes the task details into an Event object.
     *
     * @param taskDetails the details of the task to decode
     * @return a new Event object with the decoded details
     * @throws InvalidDateFormatException if the date in the task details is not in the expected format
     */
    public static Event decode(String taskDetails) throws InvalidDateFormatException {
        String[] detailParts = taskDetails.split("\\(from: ");
        String name = detailParts[0].trim();

        String[] dateDetails = detailParts[1].split("to: ");
        String fromDate = dateDetails[0].trim();
        String toDate = dateDetails[1].substring(0, dateDetails[1].length() - 1).trim();

        String fromDateFormatted = LocalDate.parse(fromDate, FORMATTER).format(NEW_DATE_FORMAT);
        String toDateFormatted = LocalDate.parse(toDate, FORMATTER).format(NEW_DATE_FORMAT);
        return new Event(name, fromDateFormatted, toDateFormatted);
    }

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof ToDo) {
            return -1;
        } else if (otherTask instanceof Deadline) {
            return this.to.compareTo(((Deadline) otherTask).date);
        } else if (otherTask instanceof Event) {
            return this.to.compareTo(((Event) otherTask).to);
        } else {
            assert false: "None of the types of tasks found in compareTo!";
            System.out.println("None of the types of tasks found in compareTo!");
            return 0;
        }
    }
}