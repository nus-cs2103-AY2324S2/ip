package parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A stub class for parsing date and time strings into LocalDateTime objects.
 * This class is used for testing purposes.
 */
public class DateTimeParserStub {

    protected DateTimeFormatter formatter;

    /**
     * Constructs a DateTimeParserStub object with a default formatter.
     */
    public DateTimeParserStub() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param timeString the string representing date and time
     * @return the parsed LocalDateTime object
     */
    public LocalDateTime parseDateTime(String timeString) {
        return LocalDateTime.parse(timeString, this.formatter);
    }
}
