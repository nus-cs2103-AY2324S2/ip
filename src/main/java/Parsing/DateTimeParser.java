package Parsing;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateTimeParser {

    protected DateTimeFormatter formatter;
    public DateTimeParser() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }

    public LocalDateTime parseDateTime(String timeString) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(timeString, this.formatter);
        return parsedDateTime;
    }
}
