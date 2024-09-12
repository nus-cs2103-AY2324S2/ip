package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the class that handles date input and interprets it accordingly.
 */
public class DateFormatter {
    /**
     * Represents an object that handles the input format that includes day, month, year, hours, minutes.
     */
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Represents an object that handles the output format that includes day, month, year, hours, minutes.
     */
    private static final DateTimeFormatter outputDateTimeFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    /**
     * Represents an object that handles the input format that includes day, month, year in this specific order.
     */
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");

    /**
     * Represents an object that handles the output format that includes day, month, year in this specific order.
     */
    private static final DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Represents an object that handles the input format that includes year, month, day in this specific order.
     */
    private static final DateTimeFormatter altDateFormat = DateTimeFormatter.ofPattern("yyyy/MM/d");

    /**
     * Represents an object that handles the output format that includes year, month, day in this specific order.
     */
    private static final DateTimeFormatter altOutputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Instantiates date time formatting class.
     */
    public DateFormatter() {
    }

    /**
     * Checks if the input is a valid date and time input.
     *
     * @param dateStr Represents the date and time extracted from instructions.
     * @return A boolean representing true if the instruction is a valid date and time.
     */
    private boolean isValidDateTime(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, timeFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input is a valid date input that includes day, month, year in this specific order.
     *
     * @param dateStr Represents the date extracted from instructions.
     * @return A boolean representing true if valid instruction and includes day, month, year in this specific order.
     */
    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input is a valid date input that includes year, month, day in this specific order.
     *
     * @param dateStr Represents the date extracted from instructions.
     * @return A boolean representing true if valid instruction and includes year, month, day in this specific order.
     */
    private boolean isValidAltDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, altDateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Converts the date based on what kind of valid input it is.
     *
     * @param dateStr The extracted instructions that is relevant to pass.
     * @return The final result that is the converted value if it is a valid format.
     */
    public String convertedDate(String dateStr) {
        if (isValidDateTime(dateStr)) {
            LocalDateTime date = LocalDateTime.parse(dateStr, timeFormat);
            return date.format(outputDateTimeFormat);
        } else if (isValidDate(dateStr)) {
            LocalDate date = LocalDate.parse(dateStr, dateFormat);
            return date.format(outputDateFormat);
        } else if (isValidAltDate(dateStr)) {
            LocalDate date = LocalDate.parse(dateStr, altDateFormat);
            return date.format(altOutputDateFormat);
        }
        return dateStr;
    }
}
