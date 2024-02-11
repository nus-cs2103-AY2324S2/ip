package jerome.common;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jerome.exception.MalformedUserInputException;

/**
 * Represents a handler for date and time operations.
 */
public class DateTimeHandler {

    // Solution adapted from:
    // https://stackoverflow.com/questions/22463062/how-can-i-parse-format-dates-with-localdatetime-java-8
    /**
     * Represents the date input format string.
     */
    public static final String DATE_INPUT_FORMAT_STRING = "yyyy-MM-dd";
    /**
     * Represents the format for parsing input dates.
     */
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT_STRING);
    /**
     * Represents the format for outputting dates.
     */
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate localDate;

    /**
     * Represents a handler for date and time operations.
     * @param dateTime date in String format to be parsed.
     * @throws MalformedUserInputException if the user input is dirty.
     */
    public DateTimeHandler(String dateTime) throws MalformedUserInputException {
        try {
            // Possible formats
            // 12 May
            // 12 May HH:mm
            // 12 May 2023
            // 12 May 2024 11:22
            // Monday
            // Monday 2 PM
            // Monday 2:00 PM
            this.localDate = LocalDate.parse(dateTime, DATE_INPUT_FORMAT);

        } catch (DateTimeException dateTimeException) {
            throw new MalformedUserInputException("\t Invalid date time format.\n"
                    + "\t Please enter in format " + DATE_INPUT_FORMAT_STRING);
        }
    }

    /**
     * Returns the string representation of LocalDate object in a storage-friendly format.
     * The format is specified by DATE_INPUT_FORMAT.
     *
     * @return a string representation of the LocalDate object in a format suitable for storage
     */
    public String toStorageString() {
        return this.localDate.format(DATE_INPUT_FORMAT);
    }

    /**
     * Returns the string representation in a different format.
     *
     * @return a string representation of LocalDate object in human-friendly format
     */
    @Override
    public String toString() {
        return this.localDate.format(DATE_OUTPUT_FORMAT);
    }
}
