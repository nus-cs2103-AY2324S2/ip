package luke.parser;

import luke.exception.DateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Represents a parser that parses a string into a LocalDateTime object and throws an
 * exception if the date is not in the correct format.
 */
public class DateTimeParser {
    private LocalDateTime date;

    /**
     * Constructs a DateTimeParser object and parses the date into a LocalDateTime object.
     *
     * @param date The date to be parsed into a LocalDateTime object from a string.
     * @throws DateException If the date is not in the correct format.
     */
    public DateTimeParser(String date) throws DateException {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("[dd/MM/yyyy]" + "[yyyy/MM/dd]" + "[yyyy-MM-dd]" + "[dd-MM-yyyy]")
                .optionalStart()
                .appendPattern("[ HH:mm]" + "[ HH]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .optionalEnd()
                .toFormatter();

        try {
            this.date = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            this.date = null;
        }

        if (this.date == null) {
            throw new DateException("Sorry! The date is not in the correct format! :'(");
        }
    }

    public LocalDateTime getDateTime() {
        return this.date;
    }

}
