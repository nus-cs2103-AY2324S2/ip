package grumblebug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser object is used to parse reasonable date formats flexibly
 * into LocalDate objects, from user and for storage into data file.
 */
public class Parser {

    private DateTimeFormatter formatter;

    Parser(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format);
    }

    /**
     * Parses date in the specified format into a LocalDate object, to be stored into a Task object.
     * @param dt the String date input.
     * @return the LocalDate with the parsed time stored.
     */
    public LocalDate parse(String dt) {
        return LocalDate.parse(dt, this.formatter);
    }
}
