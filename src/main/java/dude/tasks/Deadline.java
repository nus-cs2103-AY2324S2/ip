package dude.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import dude.exceptions.InvalidArgumentException;
import dude.exceptions.InvalidDescriptionException;
import dude.exceptions.InvalidFormatException;
import dude.utils.Utils;


/**
 * The Deadline class represents a task with a description and a deadline.
 */
public class Deadline extends Task {

    private final LocalDateTime deadlineDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadlineDate = by;
    }

    /**
     * Static method to create a Deadline object from parsing a string.
     * Expects a string in the format "deadline *description* /by *deadline_date*".
     *
     * @param s The string to be parsed into a Deadline object.
     * @return The Deadline object created from the string.
     * @throws InvalidFormatException      If the format of the string is invalid.
     * @throws InvalidDescriptionException If the description of the deadline is empty.
     * @throws InvalidArgumentException    If the 'by' of the deadline is empty.
     */
    public static Deadline from(String s) throws InvalidFormatException,
            InvalidDescriptionException, InvalidArgumentException {
        String rest = Utils.discardFirstWord(s.trim()).trim();
        String[] arr = rest.split(" ");

        validateBy(arr);
        int byIndex = Utils.findIndex(arr, "/by");

        String description = extractDescription(byIndex, arr);
        String by = extractBy(byIndex, arr);
        LocalDateTime dt = extractDateFromString(by);

        return new Deadline(description, dt);
    }

    /**
     * Returns the deadline of the Deadline object.
     *
     * @return The deadline date-time of the Deadline object.
     */
    public LocalDateTime extractBy() {
        return deadlineDate;
    }

    private static String extractBy(int byIndex, String[] arr) throws InvalidArgumentException {
        String by = "";
        for (int i = byIndex + 1; i < arr.length; i++) {
            by += arr[i] + " ";
        }
        by = by.trim();
        if (by.isEmpty()) {
            throw new InvalidArgumentException("The 'by' of a deadline cannot be empty. "
                    + "Follow this format: deadline <description> /by <deadline date time>");
        }
        return by;
    }

    private static LocalDateTime extractDateFromString(String by) throws InvalidFormatException {
        try {
            LocalDateTime dt = parseDate(by);
            return dt;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date format after '/by'. "
                    + "Use d/M/yyyy or d/M/yyy H:m in 24-hour format");
        }
    }

    private static String extractDescription(int byIndex, String[] arr) throws InvalidDescriptionException {
        String description = "";
        for (int i = 0; i < byIndex; i++) {
            description += arr[i] + " ";
        }

        description = description.trim();
        if (description.isEmpty()) {
            throw new InvalidDescriptionException("The description of a deadline cannot be empty.");
        }
        return description;
    }

    private static void validateBy(String[] arr) throws InvalidFormatException {
        int byOccurences = Utils.countOccurrences(arr, "/by");
        if (byOccurences == 0 || byOccurences > 1) {
            throw new InvalidFormatException("deadline", "format: deadline <description> /by <deadline date>. "
                    + "Provide one and only one '/by'.");
        }
    }


    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(deadlineDate) + ")";
    }

    /**
     * Returns whether the object is equal to this object.
     *
     * @param object The object to be compared.
     * @return Whether the object is equal to this object.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline t = (Deadline) object;
            return t.getDescription().equals(this.getDescription()) && t.extractBy().equals(this.extractBy());
        }
        return false;
    }
}
