package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Deadline task.
 * It extends the Task class with a specific due date and a specific string representation.
 */
public class Deadline extends Task {
    private LocalDate date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter NEW_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Constructs a new Deadline task with the specified name and due date.
     *
     * @param name the name of the Deadline task
     * @param date the due date of the task
     * @throws InvalidDateFormatException if the date format is invalid
     */
    public Deadline(String name, String date) throws InvalidDateFormatException {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The returned string includes the task type ([D]), the string representation of the superclass, and the due date.
     *
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        String date = String.format(" (by: %s)", this.date.format(FORMATTER));
        return "[D]" + super.toString() + date;
    }

    /**
     * Returns the encoded version of Deadline
     *
     * @return Encoded String version of the Deadline. Currently, uses toString() to encode.
     */
    public String encode() {
        return toString();
    }

    /**
     * Decodes the task details into a Deadline object.
     *
     * @param taskDetails the details of the task to decode
     * @return a new Deadline object with the decoded details
     * @throws InvalidDateFormatException if the date in the task details is not in the expected format
     */
    public static Deadline decode(String taskDetails) throws InvalidDateFormatException {
        String[] detailParts = taskDetails.split("\\(by: ");
        String name = detailParts[0].trim();

        String date = detailParts[1].substring(0, detailParts[1].length() - 1).trim();
        String dateFormatted = LocalDate.parse(date, FORMATTER).format(NEW_DATE_FORMAT);
        return new Deadline(name, dateFormatted);
    }
}