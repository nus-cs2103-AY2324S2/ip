package panna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a date parser which converts between LocalDate formats.
 */
public class Parser {

    private DateTimeFormatter df;

    /**
     * Constructor method
     * @param format
     */
    Parser(String format) {
        df = DateTimeFormatter.ofPattern(format);
    }

    /**
     * Parses the date in the format initialized in the constructor
     * @param dt
     * @return LocalDate object in format.
     */
    public LocalDate parse(String dt) {
        return LocalDate.parse(dt, df);
    }
}
